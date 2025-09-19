package com.teste.acdnb.infrastructure.dto;

import com.teste.acdnb.core.domain.mensalidade.enums.FormaPagamento;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoManualDTO(
        Long alunoId,
        StatusPagamento status,
        LocalDateTime dataPagamento,
        BigDecimal valorPago,
        FormaPagamento formaPagamento
) {
}
