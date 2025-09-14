package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.application.usecase.mensalidade.dto.RelatorioMensalidade;

import java.util.List;

public interface GerarRelatorioMensalidadePorMes {
    public List<RelatorioMensalidade> execute();
}
