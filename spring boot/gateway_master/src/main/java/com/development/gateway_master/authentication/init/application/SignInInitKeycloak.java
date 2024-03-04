package com.development.gateway_master.authentication.init.application;

import com.development.gateway_master.authentication.init.domain.SignInInit;
import com.development.gateway_master.authentication.init.infrastructure.response.SignInInitResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import com.development.gateway_master.shared.keycloak.domain.Keycloak;
import org.springframework.stereotype.Service;

@Service
public class SignInInitKeycloak extends SignInInit {

    private final Keycloak keycloak;

    public SignInInitKeycloak(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public boolean support(AuthenticationMethod authenticationMethod) {
        return authenticationMethod.equals(AuthenticationMethod.KEYCLOAK);
    }

    @Override
    public SignInInitResponse apply() {
        return keycloak.getLoginUrl();
    }
}
