package com.budgit.validation;

import com.budgit.dto.BudgetDTO;
import com.budgit.exception.BlankFieldException;
import com.budgit.exception.EmptyFieldException;
import com.budgit.hateoas.model.ExpenseModel;
import com.budgit.table.Budget;

import java.util.Objects;

/**
 *
 * Validates {@link com.budgit.dto.BudgetDTO}.
 */
public class BudgetDtoValidator {

    /**
     * Entry point of validation.
     */
    public BudgetDTO validate(BudgetDTO budgetDTO) {
        return nonEmpty(budgetDTO);
    }

    /**
     * Checks for empty fields.
     */
    public BudgetDTO nonEmpty(BudgetDTO budgetDTO) {
        if (budgetDTO.get_month().equals("") )
            throw new EmptyFieldException("Month ");
        if (budgetDTO.getIncome().equals(""))
            throw new EmptyFieldException("Income ");
        if (budgetDTO.getBalance().equals(""))
            throw new EmptyFieldException("Balance ");

        return nonBlank(budgetDTO);
    }

    /**
     *
     * Caller of function that checks for blank fields
     */
    public BudgetDTO nonBlank(BudgetDTO budgetDTO) {
        if (isBlank(budgetDTO.get_month()))
            throw new BlankFieldException("Month ");
        if (isBlank(budgetDTO.getIncome()))
            throw new BlankFieldException("Income ");
        if (isBlank(budgetDTO.getIncomeStreams()))
            throw new BlankFieldException("Income-streams ");
        if (isBlank(budgetDTO.getBalance()))
            throw new BlankFieldException("Balance ");

        return budgetDTO;
    }

    /**
     *
     * Checks whether field is blank.
     */
    public boolean isBlank(String value) {
        char chHolder = ' ';

        for (char ch : value.toCharArray()) {
            if (ch == ' ') {
                chHolder = ch;
            } else {
                chHolder = ch;
                break;
            }
        }

        return chHolder == ' ';

    }
}
