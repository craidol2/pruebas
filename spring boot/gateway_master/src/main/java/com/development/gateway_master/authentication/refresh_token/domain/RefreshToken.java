package com.development.gateway_master.authentication.refresh_token.domain;

import com.development.gateway_master.authentication.refresh_token.infrastructure.request.RefreshTokenRequest;
import com.development.gateway_master.authentication.refresh_token.infrastructure.response.RefreshTokenResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;

public abstract class RefreshToken {

    public abstract boolean support(AuthenticationMethod authenticationMethod);

    public abstract RefreshTokenResponse apply(RefreshTokenRequest request);
}
