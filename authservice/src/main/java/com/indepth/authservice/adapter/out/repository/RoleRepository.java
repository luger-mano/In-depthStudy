package com.indepth.authservice.adapter.out.repository;

import com.indepth.authservice.adapter.out.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
