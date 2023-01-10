package com.budgit.service;

import com.budgit.data.ExpenseRepository;
import com.budgit.table.Expense;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.budgit.table.Expense.Type.Luxury;
import static com.budgit.table.Expense.Type.Necessity;

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
    public Flux<Expense> save(List<Expense> expenses, Long budgetId) {
        for (Expense expense : expenses) {
            if (expense.getType().equals(Necessity.name()) || expense.getType().equals(Luxury.name())) {
                expense.setId(budgetId); //patronId needs to be set too
            } else {
                throw new RuntimeException("Invalid value for 'type' field.");
            }
        }
        return expenseRepository.saveAll(expenses);
    }

//    public Flux<Expense> findAllExpenses() {
//
//        return expenseRepository.findAll();
//    }
}
