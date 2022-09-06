package com.budgit.hateoas.model;

import com.budgit.table.Patron;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class PatronModel extends RepresentationModel<PatronModel> {

    //private String photo;
    private String firstName;
    private String lastName;
    private String otherNames;
    private String country;
    private String state;
    private String lga;
    private String city;
    private String sex;
    private short cateringFor;
    private String email;
    private String password;
    private LocalDateTime createdAt;


    public PatronModel(Patron patron) {
        //this.photo = patron.getPhoto();
        this.firstName = patron.getFirstName();
        this.lastName = patron.getLastName();
        this.otherNames = patron.getOtherNames();
        this.country = patron.getCountry();
        this.state = patron.getState();
        this.lga = patron.getLga();
        this.city = patron.getCity();
        this.sex = patron.getSex();
        this.cateringFor = patron.getCateringFor();
        this.email = patron.getEmail();
        this.password = patron.getPassword();
        this.createdAt = patron.getCreatedAt();
    }
}