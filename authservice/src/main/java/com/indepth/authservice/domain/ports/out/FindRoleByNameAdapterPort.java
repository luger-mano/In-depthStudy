package com.indepth.authservice.domain.ports.out;

import com.indepth.authservice.adapter.out.entities.Role;

public interface FindRoleByNameAdapterPort {
    Role findByName(String name);
}
