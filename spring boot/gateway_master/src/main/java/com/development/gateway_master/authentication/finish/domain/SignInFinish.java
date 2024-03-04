package com.development.gateway_master.authentication.finish.domain;

import com.development.gateway_master.authentication.finish.infrastructure.request.SignInFinishRequest;
import com.development.gateway_master.authentication.finish.infrastructure.response.SignInFinishResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;

public abstract class SignInFinish {

    public abstract boolean support(AuthenticationMethod authenticationMethod);

    public abstract SignInFinishResponse apply(SignInFinishRequest request);
}
