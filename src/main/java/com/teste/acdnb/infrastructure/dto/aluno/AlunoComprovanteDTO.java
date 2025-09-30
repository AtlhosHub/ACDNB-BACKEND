package com.teste.acdnb.infrastructure.dto.aluno;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlunoComprovanteDTO(
        int idMensalidade,
        int id,
        String nome,
        boolean ativo,
        LocalDateTime dataEnvio,
        LocalDate dataVencimento,
        String status,
        String formaPagamento,
        BigDecimal valor,
        Boolean desconto,
        Boolean automatico
) {
}
