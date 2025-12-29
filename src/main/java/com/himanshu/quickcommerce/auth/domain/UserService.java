package com.himanshu.quickcommerce.auth.domain;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.auth.domain.contract.LoginResponse;
import com.himanshu.quickcommerce.auth.domain.entity.AppUser;
import com.himanshu.quickcommerce.auth.persistence.UserRepository;
import com.himanshu.quickcommerce.security.JwtUtil;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public UserService(UserRepository repo, PasswordEncoder encoder, JwtUtil jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public void signup(String email, String password) {
        repo.save(AppUser.create(
                email,
                encoder.encode(password)));
    }

    public LoginResponse login(String email, String password) {
        AppUser user = repo.findByEmail(
                email)
                .orElseThrow(() ->new BadCredentialsException("Invalid credentials"));

        if (!encoder.matches(password, user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new LoginResponse(jwt.generateToken(user.getEmail()));
    }
}
