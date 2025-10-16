package com.teste.acdnb.infrastructure.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

    public static LocalTime parse(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        return LocalTime.parse(dateStr, FORMATTER);
    }
}
