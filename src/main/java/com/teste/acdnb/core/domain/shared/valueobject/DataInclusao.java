package com.teste.acdnb.core.domain.shared.valueobject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teste.acdnb.core.application.exception.InvalidDataException;

import java.time.LocalDateTime;

public class DataInclusao {
    private LocalDateTime value;

    private DataInclusao(LocalDateTime value) {
        this.value = value;
    }

    @JsonCreator
    public static DataInclusao of(@JsonProperty("value") LocalDateTime value) {
        if(value == null || value.isAfter(LocalDateTime.now())) {
            throw new InvalidDataException("Data de inclusão inválida");
        }

        return new DataInclusao(value);
    }

    public LocalDateTime getValue() {
        return value;
    }
}
