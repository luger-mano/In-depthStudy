package com.indepth.authservice.domain.ports.out;

import com.indepth.authservice.adapter.out.entities.UserCredential;

import java.util.Optional;

public interface FindUserByEmailAdapterPort {
    Optional<UserCredential> findByEmail(String email);
}
