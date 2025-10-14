package com.teste.acdnb.core.domain.mensalidade;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.entities.Comprovante;
import com.teste.acdnb.core.domain.mensalidade.enums.FormaPagamento;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class Mensalidade {
    private int id;
    private Aluno aluno;
    private LocalDate dataVencimento;
    private LocalDateTime dataPagamento;
    private StatusPagamento statusPagamento;
    private boolean alteracaoAutomatica = false;
    private ValorMensalidade valor;
    private FormaPagamento formaPagamento;
    private Comprovante comprovante;

    public Mensalidade() {}

    public Mensalidade(int id, Optional<FormaPagamento> formaPagamento, ValorMensalidade valor, boolean alteracaoAutomatica, StatusPagamento statusPagamento, LocalDateTime dataPagamento, LocalDate dataVencimento, Aluno aluno, Optional<Comprovante> comprovante) {
        this.id = id;
        this.formaPagamento = formaPagamento.orElse(null);
        this.valor = valor;
        this.alteracaoAutomatica = alteracaoAutomatica;
        this.statusPagamento = statusPagamento;
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
        this.aluno = aluno;
        this.comprovante = comprovante.orElse(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public boolean isAlteracaoAutomatica() {
        return alteracaoAutomatica;
    }

    public void setAlteracaoAutomatica(boolean alteracaoAutomatica) {
        this.alteracaoAutomatica = alteracaoAutomatica;
    }

    public ValorMensalidade getValor() {
        return valor;
    }

    public void setValor(ValorMensalidade valor) {
        this.valor = valor;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Comprovante getComprovante() {
        return comprovante;
    }

    public void setComprovante(Comprovante comprovante) {
        this.comprovante = comprovante;
    }
}
