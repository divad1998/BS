package com.budgit.data;

import com.budgit.table.Expense;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ExpenseRepository extends ReactiveCrudRepository<Expense, Long> {

    Mono<Void> deleteByPatronId(Long patronId);
}