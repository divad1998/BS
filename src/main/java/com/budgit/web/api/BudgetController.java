package com.budgit.web.api;

import com.budgit.dto.BudgetDTO;
import com.budgit.service.BudgetService;
import com.budgit.validation.BudgetDtoValidator;
import com.budgit.validation.PatronValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BudgetDTO> createBudget(@RequestBody BudgetDTO budgetDTO) {
        final var validator = new BudgetDtoValidator();
        BudgetDTO validatedBudgetDto = validator.validate(budgetDTO);

        Mono<BudgetDTO> savedBudgetDTOMono = budgetService.create(validatedBudgetDto);

        return savedBudgetDTOMono;
    }

    //ToDo: edit this
//    @GetMapping
//    public Flux<Budget> fetchBudgets() {
//
//    }
}