package com.teste.acdnb.infrastructure.dto;

import com.teste.acdnb.core.domain.mensalidade.enums.TipoRetornoPagamento;

import java.math.BigDecimal;
import java.util.List;

public record RespostaPagementoDTO(
        String emailDestinatario,
        TipoRetornoPagamento tipoRetorno,
        String mensagem,
        BigDecimal valorRecebido,
        BigDecimal valorFaltante,
        BigDecimal valorExcedente,
        List<Long> idsProcessados,
        List<Long> idsComDesconto
) {}
