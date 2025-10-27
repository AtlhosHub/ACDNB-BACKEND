package com.teste.acdnb.core.domain.mensalidade.entities;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.valueobject.ValoresComprovante;
import com.teste.acdnb.core.domain.shared.valueobject.Nome;

import java.time.LocalDateTime;

public class Comprovante {
    private int id;
    private Aluno aluno;
    private Nome nomeRemetente;
    private String bancoOrigem;
    private ValoresComprovante valores;
    private LocalDateTime dataEnvio;
    private String contaDestino;
    private String bancoDestino;

    public Comprovante() {}

    public Comprovante(int id, Aluno aluno, Nome nomeRemetente, String bancoOrigem, ValoresComprovante valores, LocalDateTime dataEnvio, String contaDestino, String bancoDestino) {
        this.id = id;
        this.aluno = aluno;
        this.nomeRemetente = nomeRemetente;
        this.bancoOrigem = bancoOrigem;
        this.valores = valores;
        this.dataEnvio = dataEnvio;
        this.contaDestino = contaDestino;
        this.bancoDestino = bancoDestino;
    }


    @Override
    public String toString() {
        return "PagamentoExtraido{" +
                "nome_remetente='" + nomeRemetente + '\'' +
                ", valor='" + valores.getValorDescontoAplicado() + '\'' +
                ", data_hora='" + dataEnvio + '\'' +
                ", banco_origem='" + bancoOrigem + '\'' +
                '}';
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nome getNomeRemetente() {
        return nomeRemetente;
    }

    public void setNomeRemetente(Nome nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    public String getBancoOrigem() {
        return bancoOrigem;
    }

    public void setBancoOrigem(String bancoOrigem) {
        this.bancoOrigem = bancoOrigem;
    }

    public ValoresComprovante getValores() {
        return valores;
    }

    public void setValores(ValoresComprovante valores) {
        this.valores = valores;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public String getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(String bancoDestino) {
        this.bancoDestino = bancoDestino;
    }
}
