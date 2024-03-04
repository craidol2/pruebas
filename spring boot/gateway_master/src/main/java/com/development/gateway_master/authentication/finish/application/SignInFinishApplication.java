package com.development.gateway_master.authentication.finish.application;

import com.development.gateway_master.authentication.finish.domain.SignInFinish;
import com.development.gateway_master.authentication.finish.infrastructure.request.SignInFinishRequest;
import com.development.gateway_master.authentication.finish.infrastructure.response.SignInFinishResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import com.development.gateway_master.shared.jwt.infrastructure.JwtReader;
import com.development.gateway_master.shared.publications.domain.Publications;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SignInFinishApplication {
    private static final List<Class<? extends com.development.gateway_master.authentication.finish.domain.SignInFinish>> signInFinishProviders;

    static {
        signInFinishProviders = List.of(
                SignInFinishFinishKeycloak.class
        );
    }

    private final ApplicationContext context;
    private final Publications publications;

    public SignInFinishApplication(ApplicationContext context, Publications publications) {
        this.context = context;
        this.publications = publications;
    }

    public SignInFinishResponse execute(SignInFinishRequest request, AuthenticationMethod authenticationMethod) {
        SignInFinishResponse response = null;

        for (Class<? extends SignInFinish> listSignInFinish : signInFinishProviders) {
            var startTransaction = context.getBean(listSignInFinish);
            if (startTransaction.support(authenticationMethod)) {
                response = startTransaction.apply(request);
                break;
            }
        }

        //create or update user
        publications.createUpdateUser(getCreateUpdateUserRequest(response.getToken()));

        return response;
    }

    private Object getCreateUpdateUserRequest(String tokenEncoded) {
        var payload = JwtReader.getTokenPayload(tokenEncoded);

        var request = new HashMap<>();
        request.put("id", payload.getSid());
        request.put("name", payload.getName());
        request.put("email", payload.getEmail());

        return request;
    }
}
