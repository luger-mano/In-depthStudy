package com.indepth.authservice.domain.usecase;

import com.indepth.authservice.adapter.out.entities.UserCredential;
import com.indepth.authservice.adapter.out.repository.FindUserByEmailAdapter;
import com.indepth.authservice.domain.ports.in.FindUserByEmailUseCasePort;

public class FindUserByEmailUseCase implements FindUserByEmailUseCasePort {


    private final FindUserByEmailAdapter findUserByEmailAdapter;

    public FindUserByEmailUseCase(FindUserByEmailAdapter findUserByEmailAdapter) {
        this.findUserByEmailAdapter = findUserByEmailAdapter;
    }

    @Override
    public UserCredential execute(String email) {
        return findUserByEmailAdapter.findByEmail(email);
    }
}
