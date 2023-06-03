package com.ohusiev.budgettracker.web.dto;

import java.time.LocalDateTime;

public record PaymentDTO(Double amount, String description, LocalDateTime creationDate, String category) {
}
