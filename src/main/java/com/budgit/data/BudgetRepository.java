package com.budgit.data;

import com.budgit.table.Budget;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BudgetRepository extends ReactiveCrudRepository<Budget, Long> {

    Mono<Void> deleteByPatronId(Long patronId);
}
