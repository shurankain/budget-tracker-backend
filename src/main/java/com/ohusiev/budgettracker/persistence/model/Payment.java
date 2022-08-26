package com.ohusiev.budgettracker.persistence.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.Nullable;

@Document(collection = "payments")
public record Payment(@Id String id, Double amount, String description, LocalDate creationDate,
                      @Nullable String category) {
}
