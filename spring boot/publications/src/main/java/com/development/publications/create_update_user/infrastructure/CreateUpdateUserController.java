package com.development.publications.create_update_user.infrastructure;

import com.development.publications.create_update_user.application.CreateUpdateUserService;
import com.development.publications.create_update_user.infrastructure.request.CreateUpdateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUpdateUserController {

    private final CreateUpdateUserService service;

    public CreateUpdateUserController(CreateUpdateUserService service) {
        this.service = service;
    }

    @PostMapping("users")
    public ResponseEntity<Void> execute(@RequestBody CreateUpdateUserRequest request) {
        service.execute(request);
        return ResponseEntity.ok().build();
    }
}
