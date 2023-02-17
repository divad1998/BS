package com.budgit.extensions;

import com.budgit.table.Budget;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.time.LocalDateTime;

public class BudgetParameterResolver implements ParameterResolver {
    public Budget budget;
    public BudgetParameterResolver() {
        budget = new Budget();
        budget.setId(1L);
        budget.set_month("January");
        budget.setIncome("N162000");
        budget.setIncomeStreams("NYSC and Scantrik Diagnostics.");
        budget.setBalance("N3000");
        budget.setCreatedAt(LocalDateTime.parse("2023-01-12T06:26:12.183725274"));
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return parameter.getType().equals(Budget.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return budget;
    }
}
