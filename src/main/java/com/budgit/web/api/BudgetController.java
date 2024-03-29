package com.budgit.web.api;

import com.budgit.dto.BudgetDTO;
import com.budgit.service.BudgetService;
import com.budgit.validation.BudgetDtoValidator;
import com.budgit.validation.PatronValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Create budget.")
    public Mono<BudgetDTO> createBudget(@RequestBody BudgetDTO budgetDTO) {
        final var validator = new BudgetDtoValidator();
        BudgetDTO validatedBudgetDto = validator.validate(budgetDTO);

        return budgetService.create(validatedBudgetDto);
    }

    @PutMapping(path = "/{budgetId}")
    @Tag(name = "Update budget by id", description = "Updates a particular budget by its id (primary key).")
    public Mono<BudgetDTO> updateBudget(@PathVariable long budgetId, @RequestBody BudgetDTO budgetDto) {
        //Algo:
        //pass both params to budgetService.update()
        //this returns BudgetDtoMono
        //return this Mono
        return budgetService.update(budgetId, budgetDto);
    }

    //ToDo: edit this
//    @GetMapping
//    public Flux<Budget> fetchBudgets() {
//
//    }
}