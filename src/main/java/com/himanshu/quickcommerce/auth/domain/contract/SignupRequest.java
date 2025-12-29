package com.himanshu.quickcommerce.auth.domain.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String password;
}
