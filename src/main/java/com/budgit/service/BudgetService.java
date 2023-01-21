package com.budgit.service;

import com.budgit.data.BudgetRepository;
import com.budgit.dto.BudgetDTO;
import com.budgit.map.Mapper;
import com.budgit.table.Budget;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BudgetService {
    public final BudgetRepository budgetRepository;
    private Mapper mapper = new Mapper();

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    /**
     *
     * Creates new budget by calling {@link BudgetRepository}
     * @return {@link BudgetDTO} Mono publishing persisted Budget.
     */
    public Mono<BudgetDTO> create(BudgetDTO budgetDTO) {
        Budget budget = mapper.toBudget(budgetDTO);
        //ToDo: get patron id from security context and set budget id
        return budgetRepository
                            .save(budget)
                            .map(mapper::toBudgetDTO);
    }
}