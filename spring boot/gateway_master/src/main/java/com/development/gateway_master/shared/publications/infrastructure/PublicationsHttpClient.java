package com.development.gateway_master.shared.publications.infrastructure;

import com.development.commons.shared.spring.object_mapper.CustomObjectMapper;
import com.development.commons.shared.spring.rest_template.HttpClientHelper;
import com.development.gateway_master.publications.create.infrastructure.request.CreatePublicationRequest;
import com.development.gateway_master.shared.publications.domain.Publications;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
public class PublicationsHttpClient extends HttpClientHelper implements Publications {
    @Value("${conf.url.publications}")
    private String baseUrl;

    public PublicationsHttpClient(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public void createUpdateUser(Object request) {
        request(UriComponentsBuilder.fromUriString(baseUrl).path("/users").toUriString(), HttpMethod.POST, request, Void.class);
    }

    @Override
    public void createPublication(CreatePublicationRequest tempRequest, String idUser) {
        var request = CustomObjectMapper.get().convertValue(tempRequest, new TypeReference<HashMap<String, Object>>() {
        });
        request.put("idUser", idUser);

        request(UriComponentsBuilder.fromUriString(baseUrl).path("/publications").toUriString(), HttpMethod.POST, request, Void.class);
    }
}
