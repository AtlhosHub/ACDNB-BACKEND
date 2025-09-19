package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.usecase.mensalidade.dto.RelatorioMensalidade;

import java.util.List;

public class GerarRelatorioMensalidadePorMesImpl implements GerarRelatorioMensalidadePorMes {
    private final MensalidadeGateway mensalidadeGateway;

    public GerarRelatorioMensalidadePorMesImpl(MensalidadeGateway gateway) {
        this.mensalidadeGateway = gateway;
    }

    @Override
    public List<RelatorioMensalidade> execute() {
        return mensalidadeGateway.gerarRelatorioMensalidadePorMes();
    }
}
