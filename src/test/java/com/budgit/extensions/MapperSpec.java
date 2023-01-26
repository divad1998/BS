package com.budgit.extensions;

import com.budgit.dto.BudgetDTO;
import com.budgit.map.Mapper;
import com.budgit.table.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({BudgetParameterResolver.class, BudgetDTOParamResolver.class})
public class MapperSpec {
    BudgetDTO budgetDTO;
    Budget budget;
    Mapper mapper = new Mapper();

    @BeforeEach
    void init(BudgetDTO resolvedBudgetDto, Budget resolvedBudget) {
        budgetDTO = resolvedBudgetDto;
        budget = resolvedBudget;
    }

    @DisplayName("Maps BudgetDto instance to Budget.")
    @Test
    void mapBudgetDtoToBudget() {
        Budget mappedBudget = mapper.toBudget(budgetDTO);
        assertEquals(budget, mappedBudget);
    }

    @DisplayName("Maps Budget instance to BudgetDto. ")
    @Test
    void mapBudgetToBudgetDto() {
        BudgetDTO mappedBudgetDto = mapper.toBudgetDTO(budget);
        assertEquals(budgetDTO, mappedBudgetDto);

    }
}