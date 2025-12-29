package com.himanshu.quickcommerce.auth.web;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAuthExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResponse handleBadCredentials(BadCredentialsException ex) {
        return ErrorResponse.create(ex, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
}
