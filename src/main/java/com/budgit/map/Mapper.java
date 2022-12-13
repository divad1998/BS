package com.budgit.map;

import com.budgit.dto.BudgetDTO;
import com.budgit.table.Budget;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import reactor.core.publisher.Mono;

/**
 *
 * maps BudgetDTO to Budget and vice versa
 */
public class Mapper {

    /**
     *
     * essentially maps BudgetDTO to Budget
     * @return essentially, resulting budget
     */
    public Mono<Budget> toBudget(Mono<BudgetDTO> budgetDTOMono) {

        return budgetDTOMono
                .map(budgetDTO -> {
                    Budget budget = new Budget();
                    budget.setMonth(budgetDTO.getMonth());
                    budget.setIncome(budgetDTO.getIncome());
                    budget.setBalance(budgetDTO.getBalance());
                    budget.setCreatedAt(budgetDTO.getCreatedAt());

                    return budget;
                });

    }

    /**
     *
     * essentially maps Budget to BudgetDTO
     * @return essentially, resulting budgetDTO
     */
    public Mono<BudgetDTO> toBudgetDTO(Mono<Budget> budgetMono) {

        return budgetMono
                .map(budget -> {
                    BudgetDTO budgetDTO = new BudgetDTO();
                    budgetDTO.setId(budget.getId());
                    budgetDTO.setMonth(budget.getMonth());
                    budgetDTO.setIncome(budget.getIncome());
                    budgetDTO.setBalance(budget.getBalance());
                    budgetDTO.setCreatedAt(budget.getCreatedAt());

                    return budgetDTO;
                });
    }
}
