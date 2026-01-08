package com.himanshu.quickcommerce.auth.domain.contract;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String accessToken;
    private String refreshToken;

    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken; 
    }
}
