package com.teste.acdnb.infrastructure.dto.aluno;

import java.time.LocalDate;

public record AlunoAniversarioDTO(
        String nome,
        LocalDate dataNascimento
) {
}
