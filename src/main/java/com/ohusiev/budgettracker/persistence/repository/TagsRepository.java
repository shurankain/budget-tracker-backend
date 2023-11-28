package com.ohusiev.budgettracker.persistence.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.ohusiev.budgettracker.persistence.model.Payment;

import reactor.core.publisher.Flux;

public interface TagsRepository extends ReactiveCrudRepository<Payment, String> {

    @Aggregation(pipeline = {"""
            {
                $unwind : "$tags"
            }
            """, """
                {
                    $project: {
                        _id: 0,
                        tags: 1
                    }
                }
            """})
    Flux<String> getAllTags();
}