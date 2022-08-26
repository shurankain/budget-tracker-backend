package com.ohusiev.budgettracker.web.dto;

import java.time.LocalDate;

public record PaymentDTO(Double amount, String description, LocalDate creationDate, String category) {
}
