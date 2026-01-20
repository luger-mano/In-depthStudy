package com.indepthstudy.userservice.domain.service;

import com.indepthstudy.userservice.domain.dto.UserCreatedEvent;
import com.indepthstudy.userservice.domain.model.UserProfile;
import com.indepthstudy.userservice.domain.repository.UserProfileRepository;
import com.indepthstudy.userservice.domain.repository.UserProfileService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;
    private final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    @Override
    @Transactional
    public void saveUser(UserCreatedEvent userCreatedEvent) {
        if (userProfileRepository.existsByAuthId(userCreatedEvent.getAuthId())) {
            logger.warn("Evento ignorado: Usuário com ID: {} já existe", userCreatedEvent.getAuthId());
            return;
        }
        var user = new UserProfile();
        user.setAuthId(userCreatedEvent.getAuthId());
        user.setEmail(userCreatedEvent.getEmail());
        user.setName(userCreatedEvent.getName());

        userProfileRepository.save(user);
    }
}
