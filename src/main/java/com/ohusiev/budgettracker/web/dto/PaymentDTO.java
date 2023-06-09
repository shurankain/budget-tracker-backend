package com.ohusiev.budgettracker.web.dto;

import com.ohusiev.budgettracker.persistence.model.Category;

import java.time.LocalDateTime;
import java.util.List;

public record PaymentDTO(Double amount, String description, LocalDateTime creationDate, Category category, List<String> tags) {
}
