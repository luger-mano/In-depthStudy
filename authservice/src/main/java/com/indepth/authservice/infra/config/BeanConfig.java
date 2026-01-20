package com.indepth.authservice.infra.config;

import com.indepth.authservice.adapter.out.repository.FindRoleByNameAdapter;
import com.indepth.authservice.adapter.out.repository.FindUserByEmailAdapter;
import com.indepth.authservice.adapter.out.repository.SaveUserAdapter;
import com.indepth.authservice.domain.ports.in.FindRoleByNameUseCasePort;
import com.indepth.authservice.domain.ports.in.FindUserByEmailUseCasePort;
import com.indepth.authservice.domain.ports.in.SaveUserUseCasePort;
import com.indepth.authservice.domain.ports.out.AuthPublisherPort;
import com.indepth.authservice.domain.usecase.FindRoleByNameUseCase;
import com.indepth.authservice.domain.usecase.FindUserByEmailUseCase;
import com.indepth.authservice.domain.usecase.SaveUserUseCase;
import com.indepth.authservice.utils.UserCredentialMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public SaveUserUseCasePort saveUserUseCasePort(SaveUserAdapter saveUserAdapter, FindUserByEmailAdapter findUserByEmailAdapter, UserCredentialMapper userCredentialMapper, BCryptPasswordEncoder passwordEncoder, AuthPublisherPort publisher) {
        return new SaveUserUseCase(saveUserAdapter, findUserByEmailAdapter, userCredentialMapper, passwordEncoder, publisher);
    }

    @Bean
    public FindUserByEmailUseCasePort findUserByEmailUseCasePort(FindUserByEmailAdapter findUserByEmailAdapter) {
        return new FindUserByEmailUseCase(findUserByEmailAdapter);
    }

    @Bean
    public FindRoleByNameUseCasePort findRoleByNameUseCasePort(FindRoleByNameAdapter findRoleByNameAdapter) {
        return new FindRoleByNameUseCase(findRoleByNameAdapter);
    }

}
