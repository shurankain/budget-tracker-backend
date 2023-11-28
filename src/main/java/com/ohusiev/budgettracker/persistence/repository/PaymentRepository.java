package com.ohusiev.budgettracker.persistence.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.ohusiev.budgettracker.persistence.model.Payment;

import reactor.core.publisher.Flux;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {

    Flux<Payment> getAllByCategoryIsNull();

    Flux<Payment> getAllByCategory(String category);

    @Aggregation(pipeline = {"""
            {
                $match: {
                    category: {$eq:  ?0},
                    creationDate: {$gte: ?1, $lt: ?2}
                }
            }
            """
    })
    Flux<Payment> getByCategoryNameAndLimitedByDates(String category, LocalDate startDate, LocalDate endDate);
}
