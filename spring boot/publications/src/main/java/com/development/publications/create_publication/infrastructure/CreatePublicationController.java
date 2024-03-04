package com.development.publications.create_publication.infrastructure;

import com.development.publications.create_publication.application.CreatePublicationService;
import com.development.publications.create_publication.infrastructure.request.CreatePublicationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePublicationController {

    private final CreatePublicationService service;

    public CreatePublicationController(CreatePublicationService service) {
        this.service = service;
    }

    @PostMapping("publications")
    public ResponseEntity<Void> execute(@RequestBody CreatePublicationRequest request) {
        service.execute(request);
        return ResponseEntity.ok().build();
    }
}
