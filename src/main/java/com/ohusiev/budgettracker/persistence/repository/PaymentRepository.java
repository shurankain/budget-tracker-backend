package com.ohusiev.budgettracker.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.ohusiev.budgettracker.persistence.model.Payment;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {
}
