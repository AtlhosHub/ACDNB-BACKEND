package com.teste.acdnb.core.domain.shared.valueobject;

import com.teste.acdnb.core.application.exception.InvalidDataException;
import java.time.LocalDate;

public class DataNascimento {
    private final LocalDate value;

    private DataNascimento(LocalDate value) {
        this.value = value;
    }

    public static DataNascimento of(LocalDate value) {
        if(value == null || value.isAfter(LocalDate.now())){
            throw new InvalidDataException("Data de nascimento inválida");
        }

        return new DataNascimento(value);
    }

    public LocalDate getValue() {
        return value;
    }
}
