package com.indepth.authservice.adapter.out.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TABLE_ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String name;


    public enum Values{
        ADMIN(1L),
        CLIENT(2L);

        final Long roleId;


        Values(Long roleId) {
            this.roleId = roleId;
        }

        public Long getRoleId() {
            return roleId;
        }
    }
    public Long getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Role() {
    }
}
