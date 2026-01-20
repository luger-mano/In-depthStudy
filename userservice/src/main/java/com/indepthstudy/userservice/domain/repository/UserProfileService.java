package com.indepthstudy.userservice.domain.repository;

import com.indepthstudy.userservice.domain.dto.UserCreatedEvent;

public interface UserProfileService {
    void saveUser(UserCreatedEvent userCreatedEvent);
}
