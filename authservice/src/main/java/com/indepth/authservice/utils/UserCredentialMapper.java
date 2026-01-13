package com.indepth.authservice.utils;

import com.indepth.authservice.adapter.in.request.UserCredentialRequestDTO;
import com.indepth.authservice.adapter.in.response.UserCredentialResponseDTO;
import com.indepth.authservice.adapter.out.entities.UserCredential;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCredentialMapper {

    UserCredential userCredentialRequestDTOtoUserCredential(UserCredentialRequestDTO userCredentialRequestDTO);
    UserCredentialResponseDTO userCredentialToUserCredentialResponseDTO(UserCredential userCredential);
}
