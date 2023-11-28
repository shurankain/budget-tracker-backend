package com.ohusiev.budgettracker.service;

import org.springframework.stereotype.Service;

import com.ohusiev.budgettracker.persistence.model.Payment;
import com.ohusiev.budgettracker.persistence.repository.PaymentRepository;
import com.ohusiev.budgettracker.service.utils.DateUtils;
import com.ohusiev.budgettracker.web.dto.PaymentDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CategoryService categoryService;
    private final TagsService tagsService;

    public PaymentService(PaymentRepository paymentRepository,
                          CategoryService categoryService,
                          TagsService tagsService) {
        this.paymentRepository = paymentRepository;
        this.categoryService = categoryService;
        this.tagsService = tagsService;
    }

    public Flux<Payment> findAll() {
        return this.paymentRepository.findAll();
    }

    public Mono<Payment> findById(String id) {
        return this.paymentRepository.findById(id);
    }

    public Mono<Payment> save(PaymentDTO paymentDTO) {
        return this.paymentRepository
                .save(convertToPayment(paymentDTO))
                .doOnSuccess(x -> {
                    categoryService.updateCache();
                    tagsService.updateCache();
                });
    }

    public Mono<Payment> edit(String id, PaymentDTO paymentDTO) {
        return this.paymentRepository
                .save(convertToPaymentWithId(id, paymentDTO))
                .doOnSuccess(x -> {
                    categoryService.updateCache();
                    tagsService.updateCache();
                });
    }

    public Mono<Void> deleteById(String id) {
        return this.paymentRepository
                .deleteById(id)
                .doOnSuccess(x -> {
                    categoryService.updateCache();
                    tagsService.updateCache();
                });
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
                paymentDTO.category(),
                paymentDTO.tags());
    }

    private Payment convertToPaymentWithId(String id, PaymentDTO paymentDTO) {
        return new Payment(id,
                paymentDTO.amount(),
                paymentDTO.description(),
                paymentDTO.creationDate(),
                paymentDTO.category(),
                paymentDTO.tags());
    }
}
