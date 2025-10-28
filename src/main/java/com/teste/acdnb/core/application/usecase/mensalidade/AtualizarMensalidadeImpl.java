package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.dto.PagamentoManualDTO;

public class AtualizarMensalidadeImpl implements AtualizarMensalidade {
    ValorMensalidadeGateway valorMensalidadeGateway;
    MensalidadeGateway mensalidadeGateway;

    public AtualizarMensalidadeImpl(ValorMensalidadeGateway valorMensalidadeGateway, MensalidadeGateway mensalidadeGateway) {
        this.valorMensalidadeGateway = valorMensalidadeGateway;
        this.mensalidadeGateway = mensalidadeGateway;
    }

    @Override
    public Mensalidade execute(Long id, PagamentoManualDTO dto){
        Mensalidade mensalidade = mensalidadeGateway.buscarMensalidadePorId(id).orElseThrow(() -> new RuntimeException("Mensalidade não encontrada"));
        mensalidade.setStatusPagamento(dto.status());
        mensalidade.setFormaPagamento(dto.formaPagamento());
        mensalidade.setDataPagamento(dto.dataPagamento());

        if(dto.status() != StatusPagamento.PAGO) {
            mensalidade.setValor(valorMensalidadeGateway.buscarValorMensalidadeAtual());
        } else {
            ValorMensalidade valor = valorMensalidadeGateway
                    .buscarValorMensalidadePorValorEManual(dto.valorPago(), true)
                    .orElseGet(() -> {
                        ValorMensalidade novoValor = new ValorMensalidade();
                        novoValor.setValor(dto.valorPago());
                        novoValor.setManualFlag(true);

                        return valorMensalidadeGateway.adicionarValorMensalidade(novoValor);
                    });

            mensalidade.setValor(valor);
        }

        return mensalidadeGateway.salvar(mensalidade);
    }
}
