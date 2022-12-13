package com.budgit.dto;

import com.budgit.table.Expense;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.util.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * primarily a Client request-data holder
 */
@Data
@NoArgsConstructor
public class BudgetDTO {

    private long id; //on initial mapping from client's data, this is null.

    //The non-nulls
    @NonNull
    private String month;
    @NonNull
    private String income;
    @NonNull
    private String balance;
    @NonNull
    private List<Expense> expenses;
    //ToDo: fix this
    //@NonNull
    private LocalDateTime createdAt;

}