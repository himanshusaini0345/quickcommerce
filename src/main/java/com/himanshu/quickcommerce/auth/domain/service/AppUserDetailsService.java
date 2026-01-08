package com.himanshu.quickcommerce.auth.domain.service;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.auth.domain.contract.LoginResponse;
import com.himanshu.quickcommerce.auth.domain.entity.AppUser;
import com.himanshu.quickcommerce.auth.domain.entity.RefreshToken;
import com.himanshu.quickcommerce.auth.domain.exception.UserAlreadyExistsException;
import com.himanshu.quickcommerce.auth.domain.model.AppUserDetails;
import com.himanshu.quickcommerce.auth.persistence.UserRepository;
import com.himanshu.quickcommerce.security.JwtService;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final RefreshTokenService refreshTokenService;

    public AppUserDetailsService(UserRepository repo, PasswordEncoder encoder, JwtService jwt,
            RefreshTokenService refreshTokenService) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
        this.refreshTokenService = refreshTokenService;
    }

    public void signup(String email, String password) {
        if (repo.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Email already registered");
        }
        repo.save(AppUser.create(
                email,
                encoder.encode(password)));
    }

    public LoginResponse login(String email, String password) {
        AppUser user = repo.findByEmail(
                email)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!encoder.matches(password, user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        UserDetails userDetails = new AppUserDetails(user);
        String accessToken = jwt.generateToken(userDetails);
        String refreshToken = refreshTokenService.create(userDetails.getUsername()).getToken();
        return new LoginResponse(accessToken, refreshToken);
    }

    public LoginResponse refresh(String refreshToken) {
        RefreshToken rt = refreshTokenService.verify(refreshToken);

        refreshTokenService.delete(refreshToken);

        RefreshToken newRToken = refreshTokenService.create(rt.getUsername());

        AppUser user = repo.findByEmail(rt.getUsername()).orElseThrow();

        String newAccessToken = jwt.generateToken(new AppUserDetails(user));

        return new LoginResponse(newAccessToken, newRToken.getToken());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new AppUserDetails(user);
    }
}
