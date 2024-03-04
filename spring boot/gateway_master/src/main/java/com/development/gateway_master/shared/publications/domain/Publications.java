package com.development.gateway_master.shared.publications.domain;

import com.development.gateway_master.publications.create.infrastructure.request.CreatePublicationRequest;

public interface Publications {
    void createUpdateUser(Object request);
    void createPublication(CreatePublicationRequest request, String idUser);
}
