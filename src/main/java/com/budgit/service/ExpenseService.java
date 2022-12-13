package com.budgit.service;

import com.budgit.data.ExpenseRepository;
import com.budgit.table.Expense;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

//    public Mono<Expense> saveExpense(Expense expense) {
//
//        if (expense.getType().equals(Necessity.name()) || expense.getType().equals(Luxury.name())) {
//            return expenseRepository.save(expense);
//        } else {
//            throw new RuntimeException("Invalid value for 'type' field.");
//        }
//    }

    /**
     *
     * saves expenses
     * @return publisher of saved expenses
     */
    //ToDo: add patronIdLongMono and set patronId in each expense
    public Flux<Expense> save(Flux<Expense> expenseFlux, Mono<Long> budgetIdMono) {
        budgetIdMono
                .map(budgetId -> {
                    for (Expense expense : expenseFlux.toIterable()) {
                        expense.setId(budgetId);
                    }
                    return null;
                })
                .subscribe();

        return expenseRepository.saveAll(expenseFlux);

    }

    public Flux<Expense> findAllExpenses() {

        return expenseRepository.findAll();
    }
}
