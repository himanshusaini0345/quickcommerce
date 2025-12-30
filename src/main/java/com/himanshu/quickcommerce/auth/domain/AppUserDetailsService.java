package com.himanshu.quickcommerce.auth.domain;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.auth.domain.contract.LoginResponse;
import com.himanshu.quickcommerce.auth.domain.entity.AppUser;
import com.himanshu.quickcommerce.auth.domain.exception.UserAlreadyExistsException;
import com.himanshu.quickcommerce.auth.domain.model.AppUserDetails;
import com.himanshu.quickcommerce.auth.persistence.UserRepository;
import com.himanshu.quickcommerce.security.JwtService;

@Service
public class AppUserDetailsService implements UserDetailsService{
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AppUserDetailsService(UserRepository repo, PasswordEncoder encoder, JwtService jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
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
                    .orElseThrow(() ->new BadCredentialsException("Invalid credentials"));

            if (!encoder.matches(password, user.getPasswordHash())) {
                throw new BadCredentialsException("Invalid credentials");
            }
            UserDetails userDetails = new AppUserDetails(user);
            return new LoginResponse(jwt.generateToken(userDetails));
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            AppUser user = repo.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return new AppUserDetails(user);
        }
}
