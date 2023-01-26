package com.budgit.extensions;

import com.budgit.dto.BudgetDTO;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.time.LocalDateTime;

public class BudgetDTOParamResolver implements ParameterResolver {
    public BudgetDTO budgetDTO;
    public BudgetDTOParamResolver() {
        budgetDTO = new BudgetDTO();
        budgetDTO.setId(1L);
        budgetDTO.set_month("January");
        budgetDTO.setIncome("N162000");
        budgetDTO.setBalance("N3000");
        budgetDTO.setCreatedAt(LocalDateTime.parse("2023-01-12T06:26:12.183725274"));
    }
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        //check for support via type
        Parameter parameter = parameterContext.getParameter();
        if (parameter.getType().equals(BudgetDTO.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return budgetDTO;
    }
}