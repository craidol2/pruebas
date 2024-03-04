package com.development.gateway_master.authentication.refresh_token.application;

import com.development.gateway_master.authentication.refresh_token.domain.RefreshToken;
import com.development.gateway_master.authentication.refresh_token.infrastructure.request.RefreshTokenRequest;
import com.development.gateway_master.authentication.refresh_token.infrastructure.response.RefreshTokenResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefreshTokenApplication {
    private static final List<Class<? extends RefreshToken>> refreshTokenProviders;

    static {
        refreshTokenProviders = List.of(RefreshTokenKeycloak.class);
    }

    private final ApplicationContext context;

    public RefreshTokenApplication(ApplicationContext context) {
        this.context = context;
    }

    public RefreshTokenResponse execute(RefreshTokenRequest request, AuthenticationMethod authenticationMethod) {
        RefreshTokenResponse response = null;

        for (Class<? extends RefreshToken> listRefreshToken : refreshTokenProviders) {
            var startTransaction = context.getBean(listRefreshToken);
            if (startTransaction.support(authenticationMethod)) {
                response = startTransaction.apply(request);
                break;
            }
        }

        return response;
    }
}
