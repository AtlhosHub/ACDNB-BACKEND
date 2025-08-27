package com.teste.acdnb.core.domain.shared.valueobject;

import java.util.regex.Pattern;

public class Celular {
    private String value;
    private static final Pattern pattern = Pattern.compile("^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$");

    private Celular(String value) {
        this.value = value;
    }

    public static Celular of(String value) {
        if(value == null || !pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("Celular inválido");
        }

        return new Celular(value);
    }

    public String getValue() {
        return value;
    }
}
