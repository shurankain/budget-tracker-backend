package com.ohusiev.budgettracker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohusiev.budgettracker.persistence.model.Payment;
import com.ohusiev.budgettracker.service.PaymentService;
import com.ohusiev.budgettracker.web.dto.CategoryDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@ResponseBody
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    Flux<Payment> get() {
        return this.paymentService.findAll();
    }

    @GetMapping("/payments/{id}")
    Mono<Payment> findById(@PathVariable String id) {
        return this.paymentService.findById(id);
    }

    @PostMapping("/payment/add")
    public Mono<Payment> add(@RequestBody Payment payment) {
        return this.paymentService.save(payment);
    }

    @DeleteMapping("/payments/{id}")
    Mono<Void> deleteById(@PathVariable String id) {
        return this.paymentService.deleteById(id);
    }

    @GetMapping("/payments/unassigned")
    Flux<Payment> getUnassignedPayments() {
        return this.paymentService.getUnassignedPayments();
    }

    @GetMapping("/categories/data")
    Flux<CategoryDTO> getDataPerCategory() {
        return this.paymentService.getAllCategoriesData();
    }
}
