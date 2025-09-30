package com.teste.acdnb.infrastructure.filter;

import java.util.List;

public record AlunoFilter(
        String nome,
        List<String> status,
        Boolean ativo,
        String dataEnvioFrom,
        String dataEnvioTo
) {
}
