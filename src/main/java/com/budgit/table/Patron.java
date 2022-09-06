package com.budgit.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "Patrons")
@Data
public class Patron {

    @Id
    private long id;

    //private String photo; //ToDo: uncomment this
    @Column(value = "firstName")
    private String firstName; //non-nullable
    @Column(value = "lastName")
    private String lastName; //non-nullable
    @Column(value = "otherNames")
    private String otherNames; //nullable
    private String country; //non-nullable
    private String state; //non-nullable
    private String lga; //non-nullable
    private String city; //non-nullable
    private String sex; //non-nullable
    @Column(value = "cateringFor")
    private short cateringFor; //non-nullable
    private String email; //non-nullable
    private String password; //non-nullable
    @Column(value = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
}