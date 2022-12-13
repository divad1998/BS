package com.budgit.hateoas.assembler;

import com.budgit.hateoas.model.ExpenseModel;
import com.budgit.table.Expense;
import com.budgit.web.api.ExpenseController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class ExpenseModelAssembler extends RepresentationModelAssemblerSupport<Expense, ExpenseModel> {

    public ExpenseModelAssembler() {
        super(ExpenseController.class, ExpenseModel.class);
    }


    @Override
    public ExpenseModel toModel(Expense expense) {
        return createModelWithId(expense.getId(), expense);
    }

    @Override
    public ExpenseModel instantiateModel(Expense expense) {
        return new ExpenseModel(expense);
    }
}
