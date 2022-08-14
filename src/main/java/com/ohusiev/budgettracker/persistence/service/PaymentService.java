package com.ohusiev.budgettracker.persistence.service;

import org.springframework.stereotype.Component;

import com.ohusiev.budgettracker.persistence.model.Payment;
import com.ohusiev.budgettracker.persistence.repository.PaymentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Flux<Payment> findAll() {
        return this.paymentRepository.findAll();
    }

    public Mono<Payment> findById(String id) {
        return this.paymentRepository.findById(id);
    }

    public Mono<Payment> save(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    public Mono<Void> deleteById(String id) {
        return this.paymentRepository.deleteById(id);
    }
}
