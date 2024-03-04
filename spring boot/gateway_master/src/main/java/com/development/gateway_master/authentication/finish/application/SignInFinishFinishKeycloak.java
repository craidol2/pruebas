package com.development.gateway_master.authentication.finish.application;

import com.development.gateway_master.authentication.finish.domain.SignInFinish;
import com.development.gateway_master.authentication.finish.infrastructure.request.SignInFinishRequest;
import com.development.gateway_master.authentication.finish.infrastructure.response.SignInFinishResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import com.development.gateway_master.shared.keycloak.domain.Keycloak;
import org.springframework.stereotype.Service;

@Service
public class SignInFinishFinishKeycloak extends SignInFinish {

    private final Keycloak keycloak;

    public SignInFinishFinishKeycloak(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public boolean support(AuthenticationMethod authenticationMethod) {
        return authenticationMethod.equals(AuthenticationMethod.KEYCLOAK);
    }

    @Override
    public SignInFinishResponse apply(SignInFinishRequest request) {
        return keycloak.getToken(request);
    }
}
