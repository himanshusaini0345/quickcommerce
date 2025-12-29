package com.himanshu.quickcommerce.auth.domain.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
}
