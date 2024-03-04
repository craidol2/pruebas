package com.development.gateway_master.authentication.init.infrastructure;

import com.development.gateway_master.authentication.init.application.SignIninitApplication;
import com.development.gateway_master.authentication.init.infrastructure.response.SignInInitResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInInitController {

    private final SignIninitApplication signIninit;

    public SignInInitController(SignIninitApplication signIninit) {
        this.signIninit = signIninit;
    }

    @GetMapping("/authentication/{authenticationMethod}/init")
    private ResponseEntity<SignInInitResponse> execute(@PathVariable AuthenticationMethod authenticationMethod) {
        return ResponseEntity.ok().body(signIninit.execute(authenticationMethod));
    }
}
