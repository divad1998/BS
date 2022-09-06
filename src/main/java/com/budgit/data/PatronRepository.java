package com.budgit.data;

import com.budgit.table.Patron;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PatronRepository extends ReactiveCrudRepository<Patron, Long> {
}
