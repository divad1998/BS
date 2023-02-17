package com.budgit.unitTests;

import com.budgit.data.BudgetRepository;
import com.budgit.dto.BudgetDTO;
import com.budgit.extensions.BudgetDTOParamResolver;
import com.budgit.extensions.BudgetParameterResolver;
import com.budgit.service.BudgetService;
import com.budgit.table.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith({MockitoExtension.class, BudgetParameterResolver.class, BudgetDTOParamResolver.class})
public class BudgetServiceSpec {
    BudgetDTO budgetDTO;
    BudgetDTO expectedBudgetDto;
    Budget budget;

    @Mock
    BudgetRepository budgetRepo;
    @InjectMocks
    BudgetService budgetService;

    @BeforeEach
    void init(BudgetDTO resolvedBudgetDTO, Budget resolvedBudget) {
        budgetDTO = new BudgetDTO();
        budgetDTO.set_month(resolvedBudgetDTO.get_month());
        budgetDTO.setIncome(resolvedBudgetDTO.getIncome());
        budgetDTO.setIncomeStreams("NYSC and Scantrik Diagnostics");
        budgetDTO.setBalance(resolvedBudgetDTO.getBalance());
        budgetDTO.setCreatedAt(resolvedBudgetDTO.getCreatedAt());

        expectedBudgetDto = resolvedBudgetDTO;
        budget = resolvedBudget;
    }

    @DisplayName("Creates budget")
    @Test
    void saveBudget() {
        Mockito.when(budgetRepo.save(any())).thenReturn(Mono.just(budget));
        var budgetDtoMono = budgetService.create(budgetDTO);
        assertEquals(expectedBudgetDto, budgetDtoMono.block());

        Mockito.verify(budgetRepo, Mockito.times(1)).save(any());
    }

    @DisplayName("Updates budget in database.")
    @Test
    void updateBudget() {
        //Algo:
        //just stub repo.save
        //call service.update
        //assert equality between resolved and .block
        //verify stubbing
        Mockito.when(budgetRepo.save(any())).thenReturn(Mono.just(budget));

        Mono<BudgetDTO> budgetDTOMono = budgetService.update(1L, budgetDTO);
        assertEquals(expectedBudgetDto, budgetDTOMono.block());

        Mockito.verify(budgetRepo, Mockito.times(1)).save(any());
    }

}