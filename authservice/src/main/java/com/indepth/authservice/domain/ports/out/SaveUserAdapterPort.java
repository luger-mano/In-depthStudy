package com.indepth.authservice.domain.ports.out;

import com.indepth.authservice.adapter.out.entities.UserCredential;

public interface SaveUserAdapterPort {
    UserCredential saveUser(UserCredential userCredential);
}
