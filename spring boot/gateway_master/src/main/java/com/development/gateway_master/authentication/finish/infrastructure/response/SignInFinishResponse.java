package com.development.gateway_master.authentication.finish.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignInFinishResponse {
    private String token;
    private String refreshToken;
}
