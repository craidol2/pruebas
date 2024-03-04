package com.development.publications.create_publication.infrastructure.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CreatePublicationRequest {
    private String idUser;
    private String title;
    private String description;
    private Integer targetAmount;
    private Date deadline;
    private Date refundDate;
    private Integer interest;
    private String image;
    private List<Long> categories;
}
