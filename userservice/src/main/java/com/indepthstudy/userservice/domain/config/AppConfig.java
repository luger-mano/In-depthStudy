package com.indepthstudy.userservice.domain.config;

import com.indepthstudy.userservice.domain.service.UserProfileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserProfileServiceImpl userProfileService() {
        return new UserProfileServiceImpl();
    }
}
