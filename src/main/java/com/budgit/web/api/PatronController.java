package com.budgit.web.api;

import com.budgit.hateoas.assembler.PatronModelAssembler;
import com.budgit.hateoas.model.PatronModel;
import com.budgit.hateoas.model.Response;
import com.budgit.dto.Login;
import com.budgit.service.PatronService;
import com.budgit.table.Patron;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * maps client request, prepares & returns response
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
    public Mono<Response> createPatron(@RequestBody Patron patron) {

        Mono<Response> response = patronService.create(Mono.just(patron));
        return response
                .map(res -> {
                    return res.add(linkTo(methodOn(PatronController.class).createPatron(null)).withRel("register"),
                                    linkTo(methodOn(PatronController.class).login(null)).withRel("login")); //ToDo: edit this endpoint
                });
    }

    @PutMapping(path = "/{patronId}")
    public Mono<PatronModel> updatePatron(@PathVariable long patronId, @RequestBody Patron patron) {

        Mono<Patron> patronMono = patronService.update(patronId, patron);
        return patronMono
                    .map(p -> {
                        return new PatronModelAssembler().toModel(p); //returns filled PatronModel
                    });
    }

    @GetMapping
    public Mono<CollectionModel<PatronModel>> fetchPatrons() {
        Mono<List<Patron>> patronListMono = patronService.fetchAll();
        return patronListMono
                    .map(patrons -> new PatronModelAssembler()
                                    .toCollectionModel(patrons)
                                    .add(linkTo(methodOn(PatronController.class).fetchPatrons()).withRel("patrons")));
    }

    @GetMapping(path = "/{patronId}")
    public Mono<PatronModel> fetchPatronById(@PathVariable Long patronId) {

        return patronService.fetchById(Mono.just(patronId))
                            .map(patron -> new PatronModelAssembler().toModel(patron));
    }

    @DeleteMapping(path = "/{patronId}")
    public Mono<Response> deletePatronById(@PathVariable Long patronId) {

        return patronService.deleteById(Mono.just(patronId))
                            .map(response -> response.add(linkTo(methodOn(PatronController.class).createPatron(null)).withRel("register")));
    }

    //ToDo: create Authentication COntroller?
    @PostMapping(path = "/login")
    public Mono<Response> login(@RequestBody Login login) {
        ///
        return null;
    }
}