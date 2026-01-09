package com.himanshu.quickcommerce.auth.domain.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.auth.domain.entity.RefreshToken;
import com.himanshu.quickcommerce.auth.persistence.RefreshTokenRepository;

import jakarta.transaction.Transactional;

@Service
public class RefreshTokenService {
    private static final long REFRESH_EXP_DAYS = 7;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public RefreshToken create(String username) {
        refreshTokenRepository.deleteByUsername(username);

        String token = UUID.randomUUID().toString();

        RefreshToken rt = RefreshToken.create(token, username, Instant.now().plus(REFRESH_EXP_DAYS, ChronoUnit.DAYS));

        return refreshTokenRepository.save(rt);
    }

    @Transactional
    public RefreshToken verify(String token) {
        RefreshToken rt = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));

        if (rt.isExpired()) {
            refreshTokenRepository.delete(rt);
            throw new BadCredentialsException("refresh token expired");
        }

        return rt;
    }
    
    @Transactional
    public void delete(String token) {
        RefreshToken rt = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));
        refreshTokenRepository.delete(rt);
    }
}
