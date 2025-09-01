package com.teste.acdnb.core.domain.aluno;

import com.teste.acdnb.core.domain.aluno.valueobject.Cep;

import java.util.ArrayList;
import java.util.List;

public class Endereco {
    private int id;
    private String logradouro;
    private String numLog;
    private String bairro;
    private String cidade;
    private String estado;
    private Cep cep;
//    private List<Aluno> alunos = new ArrayList<>();

    public Endereco(){}

    public Endereco(int id, String logradouro, String numLog, String bairro, String cidade, String estado, Cep cep){//, List<Aluno> alunos) {
        if(logradouro == null || logradouro.isEmpty()){
            throw new IllegalArgumentException("O logradouro não pode ficar em branco");
        }
        if(numLog == null || numLog.isEmpty()){
            throw new IllegalArgumentException("O número não pode ficar em branco");
        }
        if(bairro == null || bairro.isEmpty()){
            throw new IllegalArgumentException("O bairro não pode ficar em branco");
        }
        if(cidade == null || cidade.isEmpty()){
            throw new IllegalArgumentException("A cidade não pode ficar em branco");
        }
        if(estado == null || estado.isEmpty()){
            throw new IllegalArgumentException("O estado não pode ficar em branco");
        }
        if(cep == null){
            throw new IllegalArgumentException("O CEP não pode ficar em branco");
        }

        this.id = id;
        this.logradouro = logradouro;
        this.numLog = numLog;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
//        this.alunos = alunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumLog() {
        return numLog;
    }

    public void setNumLog(String numLog) {
        this.numLog = numLog;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

//    public List<Aluno> getAlunos() {
//        return alunos;
//    }
//
//    public void setAlunos(List<Aluno> alunos) {
//        this.alunos = alunos;
//    }
}
