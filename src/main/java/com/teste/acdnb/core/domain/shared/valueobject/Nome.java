package com.teste.acdnb.core.domain.shared.valueobject;

import com.teste.acdnb.core.application.exception.InvalidDataException;

import java.util.regex.Pattern;

public class Nome {
    private String value;
    private static final Pattern pattern = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$");

    private Nome(String value) { this.value = value; }

    public static Nome of(String value) {
        if(value == null || !pattern.matcher(value).matches()) {
            throw new InvalidDataException("Nome inválido");
        }

        return new Nome(value);
    }

    public String getValue() {
        return value;
    }
}
