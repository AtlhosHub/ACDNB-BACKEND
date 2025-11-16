package com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.infrastructure.dto.mensaldiade.NovoValorMensalidadeDTO;

import java.time.LocalDateTime;

public class AdicionarValorMensalidadeImpl implements AdicionarValorMensalidade {
    ValorMensalidadeGateway valorMensalidadeGateway;

    public AdicionarValorMensalidadeImpl(ValorMensalidadeGateway valorMensalidadeGateway) {
        this.valorMensalidadeGateway = valorMensalidadeGateway;
    }

    @Override
    public ValorMensalidade execute(NovoValorMensalidadeDTO novoValorMensalidadeDTO) {
        ValorMensalidade novoValor = toValorMensalidade(novoValorMensalidadeDTO);



        return valorMensalidadeGateway.adicionarValorMensalidade(novoValor);
    }

    public ValorMensalidade toValorMensalidade(NovoValorMensalidadeDTO novoValorMensalidadeDTO) {
        ValorMensalidade novoValor = new ValorMensalidade();

        novoValor.setValor(novoValorMensalidadeDTO.valor());
        novoValor.setDataInclusao(LocalDateTime.now());

        return novoValor;
    }
}
