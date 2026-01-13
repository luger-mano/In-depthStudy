package com.indepth.authservice.domain.ports.in;

import com.indepth.authservice.adapter.out.entities.UserCredential;

public interface FindUserByEmailUseCasePort {
    UserCredential execute(String email);
}
