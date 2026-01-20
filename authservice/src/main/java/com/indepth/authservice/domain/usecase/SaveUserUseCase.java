package com.indepth.authservice.domain.usecase;

import com.indepth.authservice.adapter.in.request.UserCredentialRequestDTO;
import com.indepth.authservice.adapter.in.response.UserCredentialResponseDTO;
import com.indepth.authservice.adapter.out.entities.Role;
import com.indepth.authservice.adapter.out.entities.UserCredential;
import com.indepth.authservice.adapter.out.repository.FindUserByEmailAdapter;
import com.indepth.authservice.adapter.out.repository.SaveUserAdapter;
import com.indepth.authservice.domain.ports.in.SaveUserUseCasePort;
import com.indepth.authservice.domain.ports.out.AuthPublisherPort;
import com.indepth.authservice.domain.user.UserCreatedEvent;
import com.indepth.authservice.utils.UserCredentialMapper;
import com.indepth.authservice.utils.constants.RabbitMQConstants;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;

public class SaveUserUseCase implements SaveUserUseCasePort {

    private final SaveUserAdapter saveUserAdapter;
    private final FindUserByEmailAdapter findUserByEmailAdapter;
    private final UserCredentialMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthPublisherPort publisher;


    public SaveUserUseCase(SaveUserAdapter saveUserAdapter, FindUserByEmailAdapter findUserByEmailAdapter, UserCredentialMapper mapper, BCryptPasswordEncoder passwordEncoder, AuthPublisherPort publisher) {
        this.saveUserAdapter = saveUserAdapter;
        this.findUserByEmailAdapter = findUserByEmailAdapter;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.publisher = publisher;
    }

    @Override
    @Transactional
    public UserCredentialResponseDTO execute(UserCredentialRequestDTO userRequestDTO) {
        try {

            Role roleClient = new Role(2L, "CLIENT");

            UserCredential userEmail = findUserByEmailAdapter.findByEmail(userRequestDTO.getEmail()).orElse(new UserCredential());

            if (!Objects.equals(userEmail.getEmail(), userRequestDTO.getEmail())) {

                UserCredential userMapp = mapper.userCredentialRequestDTOtoUserCredential(userRequestDTO);
                userMapp.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
                userMapp.setRoles(Set.of(roleClient));
                userMapp.setActive(true);

                UserCredential userSaved = saveUserAdapter.saveUser(userMapp);
                UserCreatedEvent event = new UserCreatedEvent(userSaved.getUserId(), userSaved.getEmail(), userSaved.getName());

                publisher.publish(RabbitMQConstants.USER_CREATED_QUEUE, event);

                return mapper.userCredentialToUserCredentialResponseDTO(userSaved);
            }
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exists.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
