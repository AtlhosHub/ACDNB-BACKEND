package com.teste.acdnb.core.domain.usuario.valueobject;

import java.util.regex.Pattern;

public class Senha {
    private String value;
    private static final Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$");

    private Senha(String value) {
        this.value = value;
    }

    public static Senha of(String value) {
        if(value == null || !pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("Senha inválida");
        }

        return new Senha(value);
    }

    public String getValue() {
        return value;
    }
}
