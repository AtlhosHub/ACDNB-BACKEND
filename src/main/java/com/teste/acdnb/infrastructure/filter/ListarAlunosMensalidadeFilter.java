package com.teste.acdnb.infrastructure.filter;

import java.util.List;

public record ListarAlunosMensalidadeFilter(
        String nome,
        List<String> status,
        Boolean ativo,
        String dataEnvioFrom,
        String dataEnvioTo,
        Integer offset,
        Integer limit
) {
}
