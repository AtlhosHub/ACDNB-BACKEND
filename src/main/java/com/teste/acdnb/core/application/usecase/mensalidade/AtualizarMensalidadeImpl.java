package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.infrastructure.dto.PagamentoManualDTO;

import java.util.List;

public class AtualizarMensalidadeImpl implements AtualizarMensalidade {
    ValorMensalidadeGateway valorMensalidadeGateway;
    MensalidadeGateway mensalidadeGateway;

    public AtualizarMensalidadeImpl(ValorMensalidadeGateway valorMensalidadeGateway, MensalidadeGateway mensalidadeGateway) {
        this.valorMensalidadeGateway = valorMensalidadeGateway;
        this.mensalidadeGateway = mensalidadeGateway;
    }

    @Override
    public Mensalidade execute(Long id, PagamentoManualDTO dto){
        ValorMensalidade valor = valorMensalidadeGateway
                .buscarValorMensalidadePorValorEManual(dto.valorPago(), true)
                .orElseGet(() -> {
                    ValorMensalidade novoValor = new ValorMensalidade();
                    novoValor.setValor(dto.valorPago());
                    novoValor.setManual(true);

                    return valorMensalidadeGateway.adicionarValorMensalidade(novoValor);
                });

        return mensalidadeGateway.buscarMensalidadePorId(id)
                .map(m -> {
                    m.setStatusPagamento(dto.status());
                    m.setDataPagamento(dto.dataPagamento());
                    m.setValor(valor);
                    m.setFormaPagamento(dto.formaPagamento());
                    return mensalidadeGateway.salvar(m);
                })
                .orElseThrow(() -> new RuntimeException("Mensalidade não encontrada"));
    }
}
