package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;

public class ContarMensalidadeComDescontoImpl implements ContarMensalidadeComDesconto {
        MensalidadeGateway mensalidadeGateway;

        public ContarMensalidadeComDescontoImpl(MensalidadeGateway gateway) {
            this.mensalidadeGateway = gateway;
        }

        public Integer execute() {
            int mensalidades = mensalidadeGateway.contarMensalidadeComDesconto();
            return mensalidades;
        }
}
