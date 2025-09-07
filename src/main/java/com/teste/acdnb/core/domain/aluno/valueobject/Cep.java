package com.teste.acdnb.core.domain.aluno.valueobject;

import java.util.regex.Pattern;

public class Cep {
    private String value;
    private static final Pattern pattern = Pattern.compile("^\\d{5}-?\\d{3}$");

    private Cep(String value) {
        this.value = value;
    }

    public static Cep of(String value) {
        if(value == null || !pattern.matcher(value).matches()){
            throw new IllegalArgumentException("CEP inválido");
        }

        return new Cep(value);
    }

    public String getValue() {
        return value;
    }
}
