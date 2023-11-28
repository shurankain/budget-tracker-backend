package com.ohusiev.budgettracker.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PaymentDTO(Double amount, String description, LocalDateTime creationDate, String category, List<String> tags) {
}
