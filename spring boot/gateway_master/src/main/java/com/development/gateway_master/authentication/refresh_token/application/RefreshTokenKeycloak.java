package com.development.gateway_master.authentication.refresh_token.application;

import com.development.gateway_master.authentication.refresh_token.domain.RefreshToken;
import com.development.gateway_master.authentication.refresh_token.infrastructure.request.RefreshTokenRequest;
import com.development.gateway_master.authentication.refresh_token.infrastructure.response.RefreshTokenResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import com.development.gateway_master.shared.keycloak.domain.Keycloak;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenKeycloak extends RefreshToken {

    private final Keycloak keycloak;

    public RefreshTokenKeycloak(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public boolean support(AuthenticationMethod authenticationMethod) {
        return authenticationMethod.equals(AuthenticationMethod.KEYCLOAK);
    }

    @Override
    public RefreshTokenResponse apply(RefreshTokenRequest request) {
        return keycloak.refreshToken(request);
    }
}
