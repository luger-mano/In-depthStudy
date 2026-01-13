package com.indepth.authservice.adapter.in.request;

import com.indepth.authservice.adapter.out.entities.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserCredentialRequestDTO {
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Boolean active;

}
