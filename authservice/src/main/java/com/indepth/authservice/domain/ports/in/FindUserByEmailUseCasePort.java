package com.indepth.authservice.domain.ports.in;

import com.indepth.authservice.adapter.out.entities.UserCredential;

import java.util.Optional;

public interface FindUserByEmailUseCasePort {
    Optional<UserCredential> execute(String email);
}
