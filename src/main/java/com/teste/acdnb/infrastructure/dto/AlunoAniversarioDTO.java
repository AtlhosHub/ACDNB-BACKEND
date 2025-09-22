package com.teste.acdnb.infrastructure.dto;

import java.time.LocalDate;

public record AlunoAniversarioDTO(
        String nome,
        LocalDate dataNascimento
) {
}
