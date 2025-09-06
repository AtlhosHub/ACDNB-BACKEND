package com.teste.acdnb.infrastructure.dto.mensalidade;

public record RelatorioMensalidadeDTO (
    int mes,
    Long atrasados,
    Long pagos,
    Long pagos_com_desconto
){}
