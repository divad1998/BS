package com.budgit.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Budgets")
@Data
public class Budget {
    @Id
    private Long id;

    //below properties are non-nullable
    private String month;
    private String income;
    private String balance;
    @Column(value = "patronId")
    private Long patronId;
    @Column(value = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
}
