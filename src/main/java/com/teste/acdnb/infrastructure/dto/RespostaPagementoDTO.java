package com.teste.acdnb.infrastructure.dto;

import com.teste.acdnb.core.domain.mensalidade.enums.TipoRetornoPagamento;
import com.teste.acdnb.core.domain.shared.valueobject.Email;

import java.math.BigDecimal;
import java.util.List;

public record RespostaPagementoDTO(
        Email email,
        TipoRetornoPagamento tipo,
        String mensagem,
        BigDecimal valorRecebido,
        BigDecimal valorRestante,
        BigDecimal valorExcedente,
        List<Long> mensalidadesPagas,
        List<Long> mensalidadesComDesconto
) {}