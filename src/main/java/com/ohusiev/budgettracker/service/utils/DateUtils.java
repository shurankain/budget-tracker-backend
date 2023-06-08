package com.ohusiev.budgettracker.service.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");

    public static LocalDate formatToDate (String dateString) {
        return LocalDate.parse(dateString, dateFormatter);
    }
}
