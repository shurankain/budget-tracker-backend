package com.ohusiev.budgettracker.web.dto;

import java.time.LocalDate;

public record DateLimiterDTO(LocalDate startDate, LocalDate endDate) {
}
