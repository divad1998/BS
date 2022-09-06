//package com.budgit.web.api;
//
//import com.budgit.data.BudgetRepository;
//import com.budgit.service.BudgetService;
//import com.budgit.table.Budget;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping(path = "/api/budgets")
//public class BudgetController {
//
//    private final BudgetService budgetService;
//
//    public BudgetController(BudgetService budgetService) {
//        this.budgetService = budgetService;
//    }
//
//    //ToDo: edit this
//    @PostMapping
//    public Mono<Budget> createBudget(@RequestBody Budget budget) {
//
//    }
//
//    //ToDo: edit this
//    @GetMapping
//    public Flux<Budget> fetchBudgets() {
//
//    }
//}
