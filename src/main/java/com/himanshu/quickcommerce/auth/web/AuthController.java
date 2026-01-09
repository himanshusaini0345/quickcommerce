package com.himanshu.quickcommerce.auth.web;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.quickcommerce.auth.domain.dto.LoginRequestDto;
import com.himanshu.quickcommerce.auth.domain.dto.LoginResponseDto;
import com.himanshu.quickcommerce.auth.domain.dto.RefreshRequestDto;
import com.himanshu.quickcommerce.auth.domain.dto.SignupRequestDto;
import com.himanshu.quickcommerce.auth.domain.service.AppUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AppUserDetailsService appUserDetailsService;

    public AuthController(AppUserDetailsService userService) {
        this.appUserDetailsService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequestDto r) {
        appUserDetailsService.signup(r.getEmail(), r.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto r) {
        return ResponseEntity.ok(appUserDetailsService.login(r.getEmail(), r.getPassword()));
    }

    @PostMapping("/refresh")
    public LoginResponseDto refresh(@RequestBody RefreshRequestDto r) {
        return appUserDetailsService.refresh(r.getRefreshToken());
    }
}
