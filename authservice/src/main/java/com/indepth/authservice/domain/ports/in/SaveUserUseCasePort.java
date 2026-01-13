package com.indepth.authservice.domain.ports.in;

import com.indepth.authservice.adapter.in.request.UserCredentialRequestDTO;
import com.indepth.authservice.adapter.in.response.UserCredentialResponseDTO;
import com.indepth.authservice.adapter.out.entities.UserCredential;

public interface SaveUserUseCasePort {
    UserCredentialResponseDTO execute(UserCredentialRequestDTO user);
}
