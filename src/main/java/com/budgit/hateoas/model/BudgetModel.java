package com.budgit.hateoas.model;

import com.budgit.dto.BudgetDTO;
import com.budgit.hateoas.assembler.BudgetModelAssembler;
import com.budgit.hateoas.assembler.ExpenseModelAssembler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class BudgetModel extends RepresentationModel<BudgetModel> {

    //The non-nulls
    private final String month;
    private final String income;
    private final String balance;
    private final CollectionModel<ExpenseModel> expenses;
    private final LocalDateTime createdAt;

    public BudgetModel(BudgetDTO budgetDTO) {
        this.month = budgetDTO.getMonth();
        this.income = budgetDTO.getIncome();
        this.balance = budgetDTO.getBalance();
        this.expenses = new ExpenseModelAssembler().toCollectionModel(budgetDTO.getExpenses());
        this.createdAt = budgetDTO.getCreatedAt();
    }
}
