package com.budgit.hateoas.assembler;

import com.budgit.dto.BudgetDTO;
import com.budgit.hateoas.model.BudgetModel;
import com.budgit.hateoas.model.PatronModel;
import com.budgit.table.Patron;
import com.budgit.web.api.BudgetController;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class BudgetModelAssembler extends RepresentationModelAssemblerSupport<BudgetDTO, BudgetModel> {

    public BudgetModelAssembler() {
        super(BudgetController.class, BudgetModel.class);
    }

    @Override
    public BudgetModel toModel(BudgetDTO budgetDTO) {
        return createModelWithId(budgetDTO.getId(), budgetDTO);
    }

    @Override
    protected BudgetModel instantiateModel(BudgetDTO budgetDTO) {
        return new BudgetModel(budgetDTO);
    }
}
