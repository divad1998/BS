package com.budgit.web.api;

import com.budgit.hateoas.assembler.PatronModelAssembler;
import com.budgit.hateoas.model.PatronModel;
import com.budgit.hateoas.model.Response;
import com.budgit.security.AuthenticationController;
import com.budgit.service.PatronService;
import com.budgit.table.Patron;
import com.budgit.validation.PatronValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * Encapsulates client's patron-request handlers
 */
@RestController
@RequestMapping(path = "/api/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Tag(name = "Create user/patron.", description = "Registers a new user/patron.")
    public Mono<Response> createPatron(@RequestBody Patron patron) {
        final PatronValidator patronValidator = new PatronValidator();
        Patron validatedPatron = patronValidator.validate(patron);
        return patronService
                            .create(validatedPatron)
                            .map(response -> {
                                return response.add(linkTo(methodOn(PatronController.class).createPatron(null)).withRel("register"),
                                linkTo(methodOn(AuthenticationController.class).login(null)).withRel("login")); //ToDo: edit this endpoint
                            });
    }

    @PutMapping(path = "/{patronId}")
    public Mono<PatronModel> updatePatron(@PathVariable long patronId, @RequestBody Patron patron) {
        //ToDo: wrap in if-block, checking whether authenticated user id matches pathVariable
        final PatronValidator patronValidator = new PatronValidator();
        Patron validatedPatron = patronValidator.validate(patron);

        return patronService
                .update(validatedPatron)
                .map(persistedPatron -> new PatronModelAssembler().toModel(persistedPatron))
                .map(patronModel -> patronModel.add(linkTo(methodOn(AuthenticationController.class).logout()).withRel("logout"))); //ToDo: logout endpoint
    }

    @GetMapping(path = "/{patronId}")
    public Mono<PatronModel> getPatronById(@PathVariable Long patronId) {

        return patronService
                .fetchById(patronId)
                .map(patron -> new PatronModelAssembler().toModel(patron));
    }

    @GetMapping
    public Mono<CollectionModel<PatronModel>> fetchPatrons() {
        return patronService
                .fetchAll()
                    .map(patrons -> new PatronModelAssembler()
                                                    .toCollectionModel(patrons)
                                                    .add(linkTo(methodOn(PatronController.class).fetchPatrons()).withRel("patrons")));
    }

    @DeleteMapping(path = "/{patronId}")
    public Mono<Void> deletePatronById(@PathVariable Long patronId) {

        return patronService
                        .deleteById(patronId);
    }
}