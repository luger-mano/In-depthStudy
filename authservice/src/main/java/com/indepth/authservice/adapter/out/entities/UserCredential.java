package com.indepth.authservice.adapter.out.entities;

import com.indepth.authservice.adapter.in.request.LoginRequestDTO;
import com.indepth.authservice.domain.user.User;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serial;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TABLE_USER_CREDENTIALS")
public class UserCredential {

    @Serial
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String name;
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "TABLE_USER_CREDENTIALS_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    private Boolean active;

    public boolean isLoginCorrect(LoginRequestDTO loginDto, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(loginDto.getPassword(), this.password);
    }

    public UserCredential(User user) {
        this.userId = getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.active = user.getActive();
    }

    public UserCredential() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
