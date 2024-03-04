package com.development.gateway_master.publications.create.infrastructure;

import com.development.gateway_master.publications.create.application.CreatePublicationService;
import com.development.gateway_master.publications.create.infrastructure.request.CreatePublicationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePublicationController {

    private final CreatePublicationService createPublicationService;

    public CreatePublicationController(CreatePublicationService createPublicationService) {
        this.createPublicationService = createPublicationService;
    }

    @PostMapping("/publications")
    private ResponseEntity<Void> execute(@RequestBody CreatePublicationRequest request, @RequestAttribute String idUser) {
        createPublicationService.execute(request, idUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
