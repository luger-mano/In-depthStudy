package com.indepth.authservice.infra.config;

import com.indepth.authservice.adapter.in.request.UserCredentialRequestDTO;
import com.indepth.authservice.adapter.out.entities.Role;
import com.indepth.authservice.adapter.out.entities.UserCredential;
import com.indepth.authservice.adapter.out.repository.SaveUserAdapter;
import com.indepth.authservice.domain.ports.in.FindRoleByNameUseCasePort;
import com.indepth.authservice.domain.ports.in.FindUserByEmailUseCasePort;
import com.indepth.authservice.domain.ports.in.SaveUserUseCasePort;
import com.indepth.authservice.utils.UserCredentialMapper;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;
import java.util.Set;

@Configuration
public class AdminConfig implements CommandLineRunner {

    private final FindRoleByNameUseCasePort findRoleByName;
    private SaveUserAdapter saveUserAdapter;
    private FindUserByEmailUseCasePort findUserByEmail;
    private UserCredentialMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminConfig(FindRoleByNameUseCasePort findRoleByName, SaveUserAdapter saveUserAdapter, FindUserByEmailUseCasePort findUserByEmail, UserCredentialMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.findRoleByName = findRoleByName;
        this.saveUserAdapter = saveUserAdapter;
        this.findUserByEmail = findUserByEmail;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = findRoleByName.execute(Role.Values.ADMIN.name());
        var userAdmin = findUserByEmail.execute("admin@email.com");

        userAdmin.ifPresentOrElse(
                user ->{
                    System.out.println("Admin já existe");
                },
                () -> {
                    UserCredential user = new UserCredential();
                    user.setName("admin");
                    user.setActive(true);
                    user.setRoles(Set.of(roleAdmin));
                    user.setEmail("admin@email.com");
                    user.setPassword("457732");
                    saveUser(user);
                }
        );

    }


    public void saveUser(UserCredential userCredential) {
        UserCredential userSaved = saveUserAdapter.saveUser(userCredential);

        System.out.println("Admin criado");
        mapper.userCredentialToUserCredentialResponseDTO(userSaved);
    }

}
