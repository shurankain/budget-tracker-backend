package com.ohusiev.budgettracker.persistence.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.ohusiev.budgettracker.persistence.model.Payment;
import com.ohusiev.budgettracker.web.dto.CategoryDTO;

import reactor.core.publisher.Flux;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {

    Flux<Payment> getAllByCategoryIsNull();

    Flux<Payment> getAllByCategory(String category);

    @Aggregation(pipeline = {"""
            {
                $group: {
                    _id: "$category",
                    name: {"$first": "$category"},
                    totalBalance: {$sum: {$add: { $toDecimal: ["$amount"]}}},
                }
            }
             """, """
            {
                $project: {
                    _id: 0,
                    name: 1,
                    totalBalance: 1
                }
            }
            """
    })
    Flux<CategoryDTO> countTotalAmountByCategory();

    @Aggregation(pipeline = {"""
            {
                $match: {
                    creationDate: {$gte: ?0, $lt: ?1}
                }
            }
            """,
            """
            {
                $group: {
                    _id: "$category",
                    name: {"$first": "$category"},
                    totalBalance: {$sum: {$add: { $toDecimal: ["$amount"]}}},
                }
            }
             """, """
            {
                $project: {
                    _id: 0,
                    name: 1,
                    totalBalance: 1
                }
            }
            """
    })
    Flux<CategoryDTO> getTotalAmountByCategoryForPeriod(LocalDate startDate, LocalDate endDate);
}
