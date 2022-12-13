package com.budgit.hateoas.model;

import com.budgit.table.Expense;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class ExpenseModel extends RepresentationModel<ExpenseModel> {
    private final String title;
    private final String type;
    private final String amount;
    private final String balance;
    private final String description;
    private final LocalDateTime createdAt;

    public ExpenseModel(Expense expense) {
        this.title = expense.getTitle();
        this.type = expense.getType();
        this.amount = expense.getAmount();
        this.balance = expense.getBalance();
        this.description = expense.getDescription();
        this.createdAt = expense.getCreatedAt();
    }
}
