package com.teste.acdnb.infrastructure.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static LocalDateTime parse(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        return LocalDateTime.parse(dateStr, FORMATTER);
    }
}
