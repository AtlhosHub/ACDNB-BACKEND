package com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;

public class BuscarValorMensalidadeAtualImpl implements BuscarValorMensalidadeAtual {
    ValorMensalidadeGateway valorMensalidadeGateway;

    public BuscarValorMensalidadeAtualImpl(ValorMensalidadeGateway valorMensalidadeGateway) {
        this.valorMensalidadeGateway = valorMensalidadeGateway;
    }

    @Override
    public ValorMensalidade execute() {
        return valorMensalidadeGateway.buscarValorMensalidadeAtual();
    }
}
