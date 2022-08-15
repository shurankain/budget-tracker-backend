package com.ohusiev.budgettracker.persistence.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payments")
public record Payment(@Id String id, Double amount, String description, LocalDateTime creationDate) {
}
