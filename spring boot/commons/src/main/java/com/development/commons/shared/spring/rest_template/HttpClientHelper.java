package com.development.commons.shared.spring.rest_template;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class HttpClientHelper {
    private final RestTemplate restTemplate;

    public HttpClientHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected <T> T request(
            UriComponentsBuilder url,
            HttpMethod method,
            HttpEntity<LinkedMultiValueMap<String, String>> httpEntity,
            Class<T> response
    ) throws RestClientException {
        return restTemplate.exchange(url.toUriString(), method, httpEntity, response).getBody();
    }

    protected <T> T request(
            String url,
            HttpMethod method,
            Object request,
            Class<T> response
    ) throws RestClientException {
        HttpEntity<Object> httpEntity = Objects.isNull(request) ? null :
                new HttpEntity<>(request);

        return restTemplate.exchange(url, method, httpEntity, response).getBody();
    }
}
