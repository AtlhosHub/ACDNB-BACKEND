package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.infrastructure.filter.FiltroMensalidadeDTO;

import java.util.List;

public class BuscarHistoricoMensalidadeImpl implements BuscarHistoricoMensalidade {
    MensalidadeGateway mensalidadeGateway;

    public BuscarHistoricoMensalidadeImpl(MensalidadeGateway gateway) {
        this.mensalidadeGateway = gateway;
    }

    @Override
    public List<Mensalidade> execute(FiltroMensalidadeDTO payload) {
        return mensalidadeGateway.buscarMensalidadePorIdEVencimento(payload);
    }
}