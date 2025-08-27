package com.teste.acdnb.core.domain.shared.valueobject;
import java.time.LocalDateTime;

public class DataInclusao {
    private LocalDateTime value;

    private DataInclusao(LocalDateTime value) {
        this.value = value;
    }

    public static DataInclusao of(LocalDateTime value) {
        if(value == null || value.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data inválida");
        }

        return new DataInclusao(value);
    }

    public LocalDateTime getValue() {
        return value;
    }
}
