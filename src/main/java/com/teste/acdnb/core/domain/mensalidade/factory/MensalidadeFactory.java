package com.teste.acdnb.core.domain.mensalidade.factory;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MensalidadeFactory {
    public List<Mensalidade> gerarMensalidades(Aluno aluno, ValorMensalidade valor, LocalDate dataReferencia, int quantidade) {
        LocalDate hoje = LocalDate.now();
        List<Mensalidade> mensalidades = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            Mensalidade m = new Mensalidade();
            m.setAluno(aluno);
            LocalDate vencimento = dataReferencia.plusMonths(i);
            m.setDataVencimento(vencimento);
            m.setValor(valor);
            m.setStatusPagamento(vencimento.isBefore(hoje) ? StatusPagamento.ATRASADO : StatusPagamento.PENDENTE);
            mensalidades.add(m);
        }

        return mensalidades;
    }
}