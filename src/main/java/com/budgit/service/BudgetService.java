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
     * creates new budget by calling BudgetRepository
     * @return BudgetDTO mono encapsulating persisted Budget with its associated expenses.
     */
    public Mono<BudgetDTO> create(Mono<BudgetDTO> budgetDTOPublisher) {

        return budgetDTOPublisher
                .map(budgetDTO -> {
                    //ToDo: study more about block()
                    Budget budget = budgetRepository.saveAll(new Mapper().toBudget(Mono.just(budgetDTO))).next().block(Duration.ofMillis(2));
                    Flux<Expense> expenseFlux = expenseService.save(Flux.fromIterable(budgetDTO.getExpenses()), Mono.just(budget.getId()));
                    BudgetDTO returnBudgetDTO = new Mapper().toBudgetDTO(Mono.just(budget)).block(Duration.ofMillis(2));
                    //ToDo: test whether null expenses are allowed.
                    List<Expense> expenses = (List<Expense>) expenseFlux.toIterable();
                    returnBudgetDTO.setExpenses(expenses);
                    return returnBudgetDTO;
                });
    }
}