package com.teste.acdnb.core.domain.shared.valueobject;

import com.teste.acdnb.core.application.exception.InvalidDataException;

import java.util.regex.Pattern;

public class Telefone {
    private String value;
    private static final Pattern pattern = Pattern.compile("^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$");

    private Telefone(String value) {
        this.value = value;
    }

    public static Telefone of(String value) {
        if (value == null) {
            return null;
        }

        if(!pattern.matcher(value).matches()) {
            throw new InvalidDataException("Número de Telefone inválido");
        }

        return new Telefone(value);
    }

    public String getValue() {
        return value;
    }
}
