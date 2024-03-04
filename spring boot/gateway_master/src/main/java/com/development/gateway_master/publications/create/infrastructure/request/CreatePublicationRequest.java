package com.development.gateway_master.publications.create.infrastructure.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CreatePublicationRequest {
    private String title;
    private String description;
    private Integer targetAmount;
    private Date deadline;
    private Date refundDate;
    private Integer interest;
    private String image;
    private List<Long> categories;
}
