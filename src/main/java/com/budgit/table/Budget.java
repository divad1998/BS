package com.budgit.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.util.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Budgets")
@Data
@NoArgsConstructor
public class Budget {
    @Id
    private Long id;

    private String monthh;
    private String income;
    private String balance;
    @Column(value = "patronId")
    @NonNull
    private Long patronId;
    @Column(value = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
}