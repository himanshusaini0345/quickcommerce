package com.himanshu.quickcommerce.auth.domain.entity;

import com.himanshu.quickcommerce.auth.domain.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String email;

    private String passwordHash;

    @Enumerated
    private Role role;

    protected AppUser() {
    }

    public static AppUser create(String email, String hash) {
        return create(email, hash, Role.CUSTOMER);
    }

    public static AppUser create(String email, String hash, Role role) {
        AppUser u = new AppUser();
        u.email = email;
        u.passwordHash = hash;
        u.role = role;
        return u;
    }
}
