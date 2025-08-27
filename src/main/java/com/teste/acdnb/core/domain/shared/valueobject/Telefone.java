package com.teste.acdnb.core.domain.shared.valueobject;

import java.util.regex.Pattern;

public class Telefone {
    private String value;
    private static final Pattern pattern = Pattern.compile("^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$");

    private Telefone(String value) {
        this.value = value;
    }

    public static Telefone of(String value) {
        if(value == null || !pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("Telefone inválido");
        }

        return new Telefone(value);
    }

    public String getValue() {
        return value;
    }
}
