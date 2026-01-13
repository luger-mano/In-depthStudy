package com.indepth.authservice.domain.usecase;

import com.indepth.authservice.adapter.out.entities.Role;
import com.indepth.authservice.domain.ports.in.FindRoleByNameUseCasePort;
import com.indepth.authservice.domain.ports.out.FindRoleByNameAdapterPort;

public class FindRoleByNameUseCase implements FindRoleByNameUseCasePort {

    private FindRoleByNameAdapterPort findRoleByNameAdapterPort;

    public FindRoleByNameUseCase(FindRoleByNameAdapterPort findRoleByNameAdapterPort) {
        this.findRoleByNameAdapterPort = findRoleByNameAdapterPort;
    }

    @Override
    public Role execute(String name) {
        return findRoleByNameAdapterPort.findByName(name);
    }
}
