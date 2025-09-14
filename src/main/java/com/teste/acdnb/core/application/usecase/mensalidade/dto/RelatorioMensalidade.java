package com.teste.acdnb.core.application.usecase.mensalidade.dto;

public record RelatorioMensalidade(
    int mes,
    Long atrasados,
    Long pagos,
    Long pagos_com_desconto
){}
