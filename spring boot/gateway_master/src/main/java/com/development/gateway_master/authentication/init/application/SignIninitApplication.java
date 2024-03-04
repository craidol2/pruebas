package com.development.gateway_master.authentication.init.application;

import com.development.gateway_master.authentication.init.domain.SignInInit;
import com.development.gateway_master.authentication.init.infrastructure.response.SignInInitResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignIninitApplication {
    private static final List<Class<? extends SignInInit>> signInFinishProviders;

    static {
        signInFinishProviders = List.of(
                SignInInitKeycloak.class
        );
    }

    private final ApplicationContext context;

    public SignIninitApplication(ApplicationContext context) {
        this.context = context;
    }

    public SignInInitResponse execute(AuthenticationMethod authenticationMethod) {
        SignInInitResponse response = null;

        for (Class<? extends SignInInit> listSignInFinish : signInFinishProviders) {
            var startTransaction = context.getBean(listSignInFinish);
            if (startTransaction.support(authenticationMethod)) {
                response = startTransaction.apply();
                break;
            }
        }

        return response;
    }
}
