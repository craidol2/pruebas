package com.development.gateway_master.authentication.init.domain;

import com.development.gateway_master.authentication.init.infrastructure.response.SignInInitResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;

public abstract class SignInInit {

    public abstract boolean support(AuthenticationMethod authenticationMethod);

    public abstract SignInInitResponse apply();
}
