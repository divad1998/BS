package com.budgit.web.api;

import com.budgit.service.ExpenseService;
import com.budgit.table.Expense;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Mono<Expense> addExpense(@RequestBody Expense expense) {

        return null;
    }

//    @GetMapping
//    public Flux<Expense> fetchExpenses() {
//
//        return expenseService.findAllExpenses();
//    }
}