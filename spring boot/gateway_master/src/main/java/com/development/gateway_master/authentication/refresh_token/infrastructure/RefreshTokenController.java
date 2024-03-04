package com.development.gateway_master.authentication.refresh_token.infrastructure;

import com.development.gateway_master.authentication.refresh_token.application.RefreshTokenApplication;
import com.development.gateway_master.authentication.refresh_token.infrastructure.request.RefreshTokenRequest;
import com.development.gateway_master.authentication.refresh_token.infrastructure.response.RefreshTokenResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshTokenController {

    private final RefreshTokenApplication refreshTokenApplication;

    public RefreshTokenController(RefreshTokenApplication refreshTokenApplication) {
        this.refreshTokenApplication = refreshTokenApplication;
    }

    @PostMapping("/authentication/{authenticationMethod}/refresh_token")
    private ResponseEntity<RefreshTokenResponse> execute(@RequestBody RefreshTokenRequest request, @PathVariable AuthenticationMethod authenticationMethod) {
        return ResponseEntity.ok().body(refreshTokenApplication.execute(request, authenticationMethod));
    }
}
