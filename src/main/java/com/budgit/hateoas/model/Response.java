package com.budgit.hateoas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Response extends RepresentationModel<Response> {

    private String message;
}