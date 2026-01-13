package com.indepth.authservice.adapter.out.repository;

import com.indepth.authservice.adapter.out.entities.Role;
import com.indepth.authservice.domain.ports.out.FindRoleByNameAdapterPort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FindRoleByNameAdapter implements FindRoleByNameAdapterPort {

    private RoleRepository repository;

    public FindRoleByNameAdapter(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role findByName(String name) {
        return repository.findByName(name).orElse(null);
    }

}
