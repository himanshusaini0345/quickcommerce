package com.himanshu.quickcommerce.auth.web;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.quickcommerce.auth.domain.AppUserDetailsService;
import com.himanshu.quickcommerce.auth.domain.contract.LoginRequest;
import com.himanshu.quickcommerce.auth.domain.contract.LoginResponse;
import com.himanshu.quickcommerce.auth.domain.contract.SignupRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AppUserDetailsService appUserDetailsService;

    public AuthController(AppUserDetailsService userService) {
        this.appUserDetailsService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest r) {
        appUserDetailsService.signup(r.getEmail(), r.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest r) {
        return ResponseEntity.ok(appUserDetailsService.login(r.getEmail(), r.getPassword()));
    }
}
