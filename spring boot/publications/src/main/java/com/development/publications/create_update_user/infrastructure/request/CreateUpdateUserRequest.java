package com.development.publications.create_update_user.infrastructure.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CreateUpdateUserRequest {
    private String id;
    private String name;
    private String email;
}
