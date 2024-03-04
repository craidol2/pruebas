package com.development.gateway_master.authentication.finish.infrastructure;

import com.development.gateway_master.authentication.finish.application.SignInFinishApplication;
import com.development.gateway_master.authentication.finish.infrastructure.request.SignInFinishRequest;
import com.development.gateway_master.authentication.finish.infrastructure.response.SignInFinishResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInFinishController {

    private final SignInFinishApplication signInFinish;

    public SignInFinishController(SignInFinishApplication signInFinish) {
        this.signInFinish = signInFinish;
    }

    @PostMapping("/authentication/{authenticationMethod}/finish")
    private ResponseEntity<SignInFinishResponse> execute(@RequestBody SignInFinishRequest request, @PathVariable AuthenticationMethod authenticationMethod) {
        return ResponseEntity.ok().body(signInFinish.execute(request, authenticationMethod));
    }
}
