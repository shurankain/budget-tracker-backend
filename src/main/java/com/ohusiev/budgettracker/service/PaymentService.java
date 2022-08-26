package com.ohusiev.budgettracker.service;

import org.springframework.stereotype.Component;

import com.ohusiev.budgettracker.persistence.model.Payment;
import com.ohusiev.budgettracker.persistence.repository.PaymentRepository;
import com.ohusiev.budgettracker.web.dto.CategoryDTO;
import com.ohusiev.budgettracker.web.dto.PaymentDTO;

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

    public Mono<Payment> save(PaymentDTO paymentDTO) {
        return this.paymentRepository.save(convertToPayment(paymentDTO));
    }

    public Mono<Void> deleteById(String id) {
        return this.paymentRepository.deleteById(id);
    }

    public Flux<Payment> getUnassignedPayments() {
        return this.paymentRepository.getAllByCategoryIsNull();
    }

    public Flux<CategoryDTO> getAllCategoriesData() {
        return this.paymentRepository.countTotalAmountByCategory();
    }

    private Payment convertToPayment(PaymentDTO paymentDTO) {
        return new Payment(null,
                paymentDTO.amount(),
                paymentDTO.description(),
                paymentDTO.creationDate(),
                paymentDTO.category());
    }
}
