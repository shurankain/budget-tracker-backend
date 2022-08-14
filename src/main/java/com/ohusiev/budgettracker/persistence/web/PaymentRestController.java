package com.ohusiev.budgettracker.persistence.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohusiev.budgettracker.persistence.model.Payment;
import com.ohusiev.budgettracker.persistence.repository.PaymentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@ResponseBody
record PaymentRestController(PaymentRepository paymentRepository) {

    @GetMapping("/payments")
    Flux<Payment> get() {
        return this.paymentRepository.findAll();
    }

    @PostMapping("/payment/add")
    public Mono<Payment> add(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }
}
