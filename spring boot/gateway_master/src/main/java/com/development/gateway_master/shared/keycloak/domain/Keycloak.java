package com.development.gateway_master.shared.keycloak.domain;

import com.development.gateway_master.authentication.finish.infrastructure.request.SignInFinishRequest;
import com.development.gateway_master.authentication.finish.infrastructure.response.SignInFinishResponse;
import com.development.gateway_master.authentication.init.infrastructure.response.SignInInitResponse;
import com.development.gateway_master.authentication.refresh_token.infrastructure.request.RefreshTokenRequest;
import com.development.gateway_master.authentication.refresh_token.infrastructure.response.RefreshTokenResponse;

public interface Keycloak {
    SignInInitResponse getLoginUrl();

    SignInFinishResponse getToken(SignInFinishRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

}
