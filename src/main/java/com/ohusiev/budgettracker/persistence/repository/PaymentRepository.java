package com.ohusiev.budgettracker.persistence.repository;

import com.ohusiev.budgettracker.persistence.model.Category;
import com.ohusiev.budgettracker.persistence.model.Payment;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {

    Flux<Payment> getAllByCategoryIsNull();

    Flux<Payment> getAllByCategory(Category category);

    @Aggregation(pipeline = {"""
            {
                $match: {
                    category: {$eq:  ?0},
                    creationDate: {$gte: ?1, $lt: ?2}
                }
            }
            """
    })
    Flux<Payment> getByCategoryNameAndLimitedByDates(Category category, LocalDate startDate, LocalDate endDate);
}
