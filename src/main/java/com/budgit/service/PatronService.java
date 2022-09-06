package com.budgit.service;

import com.budgit.data.BudgetRepository;
import com.budgit.data.ExpenseRepository;
import com.budgit.data.PatronRepository;
import com.budgit.hateoas.assembler.PatronModelAssembler;
import com.budgit.hateoas.model.PatronModel;
import com.budgit.hateoas.model.Response;
import com.budgit.table.Patron;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PatronService {

    private final PatronRepository patronRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public PatronService(PatronRepository patronRepository, BudgetRepository budgetRepository, ExpenseRepository expenseRepository) {

        this.patronRepository = patronRepository;
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    /**
     *
     * persists patron via PatronRepo
     */
    public Mono<Response> create(Mono<Patron> patronMono) {
        patronRepository.saveAll(patronMono).subscribe();
        return Mono.just(new Response("Cheers, Patron."));
    }

    /**
     *
     * updates patron
     */
    public Mono<Patron> update(long patronId, Patron patron) {

        patron.setId(patronId);
        return patronRepository.save(patron); //this works reactively
    }

    /**
     *
     * find patrons via repo
     */
    public Mono<List<Patron>> fetchAll() {
       return patronRepository
                        .findAll()
                            .collectSortedList((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));
    }

    /**
     *
     * find patron by id
     */
    public Mono<Patron> fetchById(Mono<Long> patronIdMono) {

        return patronRepository.findById(patronIdMono);
    }

    /**
     *
     * deletes patron by id
     */
    public Mono<Response> deleteById(Mono<Long> patronIdMono) {

        patronIdMono
                .map(patronId -> {
                    expenseRepository.deleteByPatronId(patronId).subscribe(); //delete patron's expenses
                    budgetRepository.deleteByPatronId(patronId).subscribe(); //delete patron's budgets
                    patronRepository.deleteById(patronId).subscribe();
                    return null;
                    })
                .subscribe();

        return Mono.just(new Response("Patron and associated budgets deleted. GoodBye!"));
    }
}