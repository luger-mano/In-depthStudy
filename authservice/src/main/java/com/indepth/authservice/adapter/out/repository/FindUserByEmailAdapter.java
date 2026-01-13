package com.indepth.authservice.adapter.out.repository;

import com.indepth.authservice.adapter.out.entities.UserCredential;
import com.indepth.authservice.domain.ports.out.FindUserByEmailAdapterPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class FindUserByEmailAdapter implements FindUserByEmailAdapterPort {

    private UserCredentialRepository repository;

    public FindUserByEmailAdapter(UserCredentialRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserCredential findByEmail(String email) {
        return repository.findByEmail(email).orElse(new UserCredential());

    }
}
