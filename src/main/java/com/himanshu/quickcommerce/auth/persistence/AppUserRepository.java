package com.himanshu.quickcommerce.auth.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himanshu.quickcommerce.auth.domain.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
