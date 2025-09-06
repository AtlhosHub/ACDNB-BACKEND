package com.teste.acdnb.core.domain.shared.valueobject;

import com.teste.acdnb.core.application.exception.InvalidDataException;

import java.util.regex.Pattern;

public class NomeSocial {
    private String value;

    private static final Pattern pattern = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$");

    private NomeSocial(String value) { this.value = value; }

    public static NomeSocial of(String value, String nome) {
        if(!pattern.matcher(value).matches() || value.equalsIgnoreCase(nome)) {
            throw new InvalidDataException("Nome Social inválido");
        }

        return new NomeSocial(value);
    }

    public String getValue() {
        return value;
    }
}
