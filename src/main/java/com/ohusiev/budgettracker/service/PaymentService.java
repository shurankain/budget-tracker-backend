package com.ohusiev.budgettracker.service;

import com.ohusiev.budgettracker.persistence.model.Category;
import com.ohusiev.budgettracker.persistence.model.Payment;
import com.ohusiev.budgettracker.persistence.repository.PaymentRepository;
import com.ohusiev.budgettracker.service.utils.DateUtils;
import com.ohusiev.budgettracker.web.dto.PaymentDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
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

    public Flux<Payment> getAllByCategoryName(String category) {
        return this.paymentRepository.getAllByCategory(category);
    }

    public Flux<Payment> getByCategoryNameAndLimitedByDates(String category, String from, String to) {
        return this.paymentRepository.getByCategoryNameAndLimitedByDates(category,
                DateUtils.formatToDate(from), DateUtils.formatToDate(to));
    }

    private Payment convertToPayment(PaymentDTO paymentDTO) {
        return new Payment(null,
                paymentDTO.amount(),
                paymentDTO.description(),
                paymentDTO.creationDate(),
                Category.valueOf(paymentDTO.category()),
                paymentDTO.tags());
    }
}
