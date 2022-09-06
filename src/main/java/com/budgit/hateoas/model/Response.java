package com.budgit.hateoas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Data
public class Response extends RepresentationModel<Response> {

    private String message;
}