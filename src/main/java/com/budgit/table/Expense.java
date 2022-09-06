package com.budgit.table;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "Expenses")
@Data
public class Expense {

    @Id
    private Long id;

    private String title;
    private String type; //allowed values: Necessity or Luxury
    private String amount;
    private String balance;
    private String description;
    @Column(value = "budgetId")
    private Long budgetId;
    @Column(value = "patronId")
    private Long patronId;
    @Column(value = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    public static enum Type {
        Necessity,
        Luxury
    }
}
