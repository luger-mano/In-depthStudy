package com.indepth.authservice.adapter.out.repository;

import com.indepth.authservice.adapter.out.entities.UserCredential;
import com.indepth.authservice.domain.ports.out.SaveUserAdapterPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class SaveUserAdapter implements SaveUserAdapterPort {


    private final UserCredentialRepository repository;

    public SaveUserAdapter(UserCredentialRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserCredential saveUser(UserCredential userCredential) {
        return this.repository.save(userCredential);
    }

}
