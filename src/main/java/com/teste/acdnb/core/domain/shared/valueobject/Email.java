package com.teste.acdnb.core.domain.shared.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teste.acdnb.core.application.exception.InvalidDataException;
import java.util.regex.Pattern;

public class Email {
    private String value;
    private static final Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private Email(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Email of(@JsonProperty("value") String value) {
        return of(value, true);
    }

    public static Email of(String value, boolean isMenor) {
        if((!isMenor && value == null) || (value != null && !pattern.matcher(value).matches())) {
            throw new InvalidDataException("E-mail inválido");
        }

        return new Email(value);
    }

    public String getValue() {
        return value;
    }
}
