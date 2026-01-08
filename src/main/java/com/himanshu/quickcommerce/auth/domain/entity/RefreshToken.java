package com.himanshu.quickcommerce.auth.domain.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Instant expiry;

    protected RefreshToken() {
    }
    
    public static RefreshToken create(String token, String username, Instant expiry) {
        RefreshToken rt = new RefreshToken();
        rt.token = token;
        rt.username = username;
        rt.expiry = expiry;
        return rt;
    }

    public boolean isExpired() {
        return expiry.isBefore(Instant.now());
    }
}
