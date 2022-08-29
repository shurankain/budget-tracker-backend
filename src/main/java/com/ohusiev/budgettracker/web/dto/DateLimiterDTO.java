package com.ohusiev.budgettracker.web.dto;

import java.time.LocalDate;

public record DateLimiterDTO(String categoryName, LocalDate startDate, LocalDate endDate) {
}
