package com.indepth.authservice.domain.user;

import com.indepth.authservice.adapter.out.entities.Role;
import com.indepth.authservice.adapter.out.entities.UserCredential;

import java.util.Set;
import java.util.UUID;

public class User {

    private UUID userId;
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Boolean active;

    public User(UUID userId, String name, String email, String password, Set<Role> roles, Boolean active) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public static User fromEntity(UserCredential userCredential) {
        return new User(userCredential.getUserId(), userCredential.getName(), userCredential.getEmail(), userCredential.getPassword(),
                userCredential.getRoles(), userCredential.getActive());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
