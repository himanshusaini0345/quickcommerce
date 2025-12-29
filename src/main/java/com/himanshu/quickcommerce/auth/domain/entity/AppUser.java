package com.himanshu.quickcommerce.auth.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    protected AppUser() {
    }

    public static AppUser create(String email, String hash) {
        AppUser u = new AppUser();
        u.email = email;
        u.passwordHash = hash;
        return u;
    }
}
