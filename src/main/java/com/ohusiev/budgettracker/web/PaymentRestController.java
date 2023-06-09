package com.ohusiev.budgettracker.web;

import com.ohusiev.budgettracker.persistence.model.Category;
import com.ohusiev.budgettracker.persistence.model.Payment;
import com.ohusiev.budgettracker.service.PaymentService;
import com.ohusiev.budgettracker.web.dto.PaymentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/payments/category")
    Flux<Payment> getAllByCategoryName(@RequestParam("category") Category category) {
        return this.paymentService.getAllByCategoryName(category);
    }

    @GetMapping("/payments/{id}")
    Mono<Payment> findById(@PathVariable String id) {
        return this.paymentService.findById(id);
    }

    @PostMapping("/payment/add")
    public Mono<Payment> add(@RequestBody PaymentDTO payment) {
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

    @PostMapping("/payments/between")
    Flux<Payment> getByCategoryNameAndLimitedByDates(@RequestParam Category category,
                                                     @RequestParam String from, @RequestParam String to) {
        return this.paymentService.getByCategoryNameAndLimitedByDates(category,
                from, to);
    }
}
