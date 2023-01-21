package com.budgit.extensions;

import com.budgit.dto.BudgetDTO;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Parameter;
import java.time.LocalDateTime;

public class BudgetDTOParamResolver implements ParameterResolver {
    public BudgetDTO budgetDTO;
    public BudgetDTOParamResolver() {
        budgetDTO = new BudgetDTO();
        budgetDTO.set_month("January");
        budgetDTO.setBalance("N3000");
        budgetDTO.setIncome("N162000");
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