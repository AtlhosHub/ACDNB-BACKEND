package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.core.domain.mensalidade.factory.MensalidadeFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GerarMensalidadeImpl implements GerarMensalidade {
    private final MensalidadeGateway mensalidadeGateway;
    private final MensalidadeFactory mensalidadeFactory;

    public GerarMensalidadeImpl(MensalidadeGateway gateway, MensalidadeFactory factory) {
        this.mensalidadeGateway = gateway;
        this.mensalidadeFactory = factory;
    }

    private final int NUMERO_PARCELAS = 12;

    @Override
    public List<Mensalidade> execute(Aluno aluno) {
        LocalDate dataReferencia = LocalDate.now().withDayOfMonth(5);
        ValorMensalidade valor = new ValorMensalidade(1, BigDecimal.valueOf(100.0), true, false);

        List<Mensalidade> mensalidades = mensalidadeFactory.gerarMensalidades(aluno, valor, dataReferencia, NUMERO_PARCELAS);

        long pendentes = mensalidadeGateway.contarMensalidadePendentesOuAtrasadas(aluno);
        if (pendentes <= 2) {
            mensalidades.addAll(mensalidadeFactory.gerarMensalidades(aluno, valor, dataReferencia.plusMonths(NUMERO_PARCELAS), NUMERO_PARCELAS));
        }

        return mensalidades;
    }
}

