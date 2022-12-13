package com.budgit.hateoas.assembler;

import com.budgit.hateoas.model.PatronModel;
import com.budgit.table.Patron;
import com.budgit.web.api.PatronController;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class PatronModelAssembler extends RepresentationModelAssemblerSupport<Patron, PatronModel> {

    public PatronModelAssembler() {
        super(PatronController.class, PatronModel.class);
    }

    @Override
    public PatronModel toModel(Patron patron) {
        return createModelWithId(patron.getId(), patron);
    }

    @Override
    protected PatronModel instantiateModel(Patron patron) {
        return new PatronModel(patron);
    }
}