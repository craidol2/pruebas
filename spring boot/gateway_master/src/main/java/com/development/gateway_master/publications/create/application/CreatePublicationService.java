package com.development.gateway_master.publications.create.application;

import com.development.gateway_master.publications.create.infrastructure.request.CreatePublicationRequest;
import com.development.gateway_master.shared.publications.domain.Publications;
import org.springframework.stereotype.Service;

@Service
public class CreatePublicationService {

    private final Publications publications;

    public CreatePublicationService(Publications publications) {
        this.publications = publications;
    }

    public void execute(CreatePublicationRequest request, String idUser) {
        publications.createPublication(request, idUser);
    }
}
