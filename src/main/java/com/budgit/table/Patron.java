package com.budgit.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.util.annotation.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor //ToDo: why did i need this?
@NoArgsConstructor
public class Patron {

    @Id
    private long id; //non-nullable after persistence

    @Column("profileMedia")
    private String profileMedia; //size of uploadable media: 10.7mb max

    @Column("firstName")
    @NonNull
    private String firstName;

    @Column("lastName")
    @NonNull
    private String lastName;

    @Column("otherNames")
    @NonNull
    private String otherNames;

    @Column("country")
    @NonNull
    private String country;

    @Column("state")
    @NonNull
    private String state;

    @Column("lga")
    @NonNull
    private String lga;

    @Column("city")
    @NonNull
    private String city;

    @Column("sex")
    @NonNull
    private String sex;

    @Column("cateringFor")
    @NonNull
    private int cateringFor;

    @Column("email")
    @NonNull
    private String email;

    @Column("password")
    @NonNull
    private String password;

    @Column("createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
}