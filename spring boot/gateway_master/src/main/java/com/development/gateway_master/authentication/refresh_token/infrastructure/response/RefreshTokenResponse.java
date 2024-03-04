package com.development.gateway_master.authentication.refresh_token.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RefreshTokenResponse {
    private String token;
    private String refreshToken;
}
