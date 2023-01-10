package com.budgit.service;

import com.budgit.data.BudgetRepository;
import com.budgit.dto.BudgetDTO;
import com.budgit.map.Mapper;
import com.budgit.table.Budget;
import com.budgit.table.Expense;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetService {

    public final BudgetRepository budgetRepository;
    public final ExpenseService expenseService;

    public BudgetService(BudgetRepository budgetRepository, ExpenseService expenseService) {
        this.budgetRepository = budgetRepository;
        this.expenseService = expenseService;
    }

    /**
     *
     * Creates new budget by calling {@link BudgetRepository}
     * @return {@link BudgetDTO} Mono publishing persisted Budget.
     */
    public Mono<BudgetDTO> create(BudgetDTO budgetDTO) {
        Budget budget = new Mapper().toBudget(budgetDTO);
        //ToDo: get patron id from security context and set budget id
        return budgetRepository
                            .save(budget)
                            .map(savedBudget -> new Mapper().toBudgetDTO(savedBudget));
    }
}