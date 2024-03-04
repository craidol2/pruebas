package com.development.gateway_master.shared.keycloak.infrastructure;

import com.development.commons.shared.spring.rest_template.HttpClientHelper;
import com.development.gateway_master.authentication.finish.infrastructure.request.SignInFinishRequest;
import com.development.gateway_master.authentication.finish.infrastructure.response.SignInFinishResponse;
import com.development.gateway_master.authentication.init.infrastructure.response.SignInInitResponse;
import com.development.gateway_master.authentication.refresh_token.infrastructure.request.RefreshTokenRequest;
import com.development.gateway_master.authentication.refresh_token.infrastructure.response.RefreshTokenResponse;
import com.development.gateway_master.shared.authentication.domain.AuthenticationMethod;
import com.development.gateway_master.shared.keycloak.domain.Keycloak;
import com.development.gateway_master.shared.keycloak.infrastructure.dto.response.KeycloakTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class KeycloakHttpClient extends HttpClientHelper implements Keycloak {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect")
    private String keyCloakUri;

    public static final String CLIENT_ID = "authentication_client";

    public KeycloakHttpClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public SignInInitResponse getLoginUrl() {
        return new SignInInitResponse(UriComponentsBuilder.fromUriString(keyCloakUri).path("/auth")
                .queryParam("response_type", "code")
                .queryParam("scope", "openid")
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_uri", getRedirectUri())
                .toUriString());
    }

    @Override
    public SignInFinishResponse getToken(SignInFinishRequest request) {
        var entityHeaders = new HttpHeaders();
        entityHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        var entityRequest = new LinkedMultiValueMap<String, String>();
        entityRequest.add("code", request.getCode());
        entityRequest.add("grant_type", "authorization_code");
        entityRequest.add("client_id", CLIENT_ID);
        entityRequest.add("redirect_uri", getRedirectUri());

        var url = UriComponentsBuilder.fromUriString(keyCloakUri).path("/token");

        var response = request(url, HttpMethod.POST, new HttpEntity<>(entityRequest, entityHeaders), KeycloakTokenResponse.class);

        return new SignInFinishResponse(response.getAccessToken(), response.getRefreshToken());
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        var entityHeaders = new HttpHeaders();
        entityHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        var entityRequest = new LinkedMultiValueMap<String, String>();
        entityRequest.add("grant_type", "refresh_token");
        entityRequest.add("client_id", CLIENT_ID);
        entityRequest.add("refresh_token", request.getRefreshToken());

        var url = UriComponentsBuilder.fromUriString(keyCloakUri).path("/token");

        var response = request(url, HttpMethod.POST, new HttpEntity<>(entityRequest, entityHeaders), KeycloakTokenResponse.class);

        return new RefreshTokenResponse(response.getAccessToken(), response.getRefreshToken());
    }

    private String getRedirectUri() {
        return UriComponentsBuilder.fromUriString("http://localhost:4220").path("authentication/{authenticationMethod}/finish").buildAndExpand(AuthenticationMethod.KEYCLOAK).toUriString();
    }
}
