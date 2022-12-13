package com.budgit.service;

import com.budgit.data.PatronRepository;
import com.budgit.hateoas.model.Response;
import com.budgit.table.Patron;
import org.springframework.data.relational.core.sql.render.RenderNamingStrategy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PatronService {
    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    /**
     * persists patron via PatronRepository and returns welcome Response.
     */
    public Mono<Response> create(Patron patron) {
       return patronRepository
               .save(patron)
               .map(persistedPatron -> new Response("Hey! Now our patron!"));
    }

    /**
     *
     * updates {@link Patron} in the database.
     * @param patron {@link Patron} to update.
     * @return {@link Mono} publishing persisted {@link Patron}.
     */
    public Mono<Patron> update(Patron patron) {
        return patronRepository.save(patron);
    }

    public Mono<Patron> fetchById(Long patronId) {
        return patronRepository.findById(patronId);
    }

    /**
     *
     * Fetches and returns a sorted list of {@link Patron}s.
     * @return sorted list of {@link Patron}s.
     */
    public Mono<List<Patron>> fetchAll() {
       return patronRepository
                        .findAll()
                            .collectSortedList((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));
    }

//    /**
//     *
//     * find patron by id
//     */
//    public Mono<Patron> fetchById(Mono<Long> patronIdMono) {
//
//        return patronRepository.findById(patronIdMono);
//    }
//
//    /**
//     *
//     * deletes patron by id
//     */
//    public Mono<Response> deleteById(Mono<Long> patronIdMono) {
//
//        patronIdMono
//                .map(patronId -> {
//                    expenseRepository.deleteByPatronId(patronId).subscribe(); //delete patron's expenses
//                    budgetRepository.deleteByPatronId(patronId).subscribe(); //delete patron's budgets
//                    patronRepository.deleteById(patronId).subscribe();
//                    return null;
//                    })
//                .subscribe();
//
//        return Mono.just(new Response("Patron and associated budgets deleted. GoodBye!"));
//    }
}