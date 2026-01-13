package com.indepth.authservice.domain.ports.in;

import com.indepth.authservice.adapter.out.entities.Role;

public interface FindRoleByNameUseCasePort {
    Role execute(String name);
}
