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
    BudgetDTO budgetDTO = new BudgetDTO();
    Budget budget;

    @Mock
    BudgetRepository budgetRepo;
    @InjectMocks
    BudgetService budgetService;

    @BeforeEach
    void init(BudgetDTO resolvedBudgetDTO, Budget resolvedBudget) {
        budgetDTO.set_month(resolvedBudgetDTO.get_month());
        budgetDTO.setIncome(resolvedBudgetDTO.getIncome());
        budgetDTO.setBalance(resolvedBudgetDTO.getBalance());

        budget = resolvedBudget;
    }

    @DisplayName("Persists budget")
    @Test
    void saveBudget() {
        var expectedBudgetDto = budgetDTO;
        expectedBudgetDto.setId(1L);
        expectedBudgetDto.setCreatedAt(LocalDateTime.parse("2023-01-12T06:26:12.183725274"));
        Mockito.when(budgetRepo.save(any())).thenReturn(Mono.just(budget));
        var budgetDtoMono = budgetService.create(budgetDTO);
        assertEquals(expectedBudgetDto, budgetDtoMono.block());

        Mockito.verify(budgetRepo, Mockito.times(1)).save(any());
    }
}