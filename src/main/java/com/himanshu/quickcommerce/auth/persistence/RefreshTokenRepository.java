package com.himanshu.quickcommerce.auth.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himanshu.quickcommerce.auth.domain.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    Optional<RefreshToken> findByToken(String token);

    void deleteByUsername(String username);
}
