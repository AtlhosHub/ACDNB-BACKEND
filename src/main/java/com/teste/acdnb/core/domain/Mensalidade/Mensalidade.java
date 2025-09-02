package com.teste.acdnb.core.domain.Mensalidade;

import com.teste.acdnb.core.domain.Mensalidade.valueobject.FormaPagamento;
import com.teste.acdnb.core.domain.Mensalidade.valueobject.StatusPagamento;
import com.teste.acdnb.core.domain.Mensalidade.valueobject.ValorMensalidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Mensalidade {
    private UUID id;
//    private Aluno aluno;
    private LocalDate dataVencimento;
    private LocalDateTime dataPagamento;
    private StatusPagamento statusPagamento;
    private boolean automatica = false;
    private ValorMensalidade valor;
    private FormaPagamento formaPagamento;
//    private Comprovante comprovante;

    public Mensalidade() {}

    private Mensalidade(FormaPagamento formaPagamento, ValorMensalidade valor, boolean automatica, StatusPagamento statusPagamento, LocalDateTime dataPagamento, LocalDate dataVencimento) {
        this.id = UUID.randomUUID();
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.automatica = automatica;
        this.statusPagamento = statusPagamento;
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

    public Mensalidade of(FormaPagamento formaPagamento, ValorMensalidade valor, boolean automatica, StatusPagamento statusPagamento, LocalDateTime dataPagamento, LocalDate dataVencimento) {

    }
}
