package com.teste.acdnb.core.domain.aluno;

import com.teste.acdnb.core.domain.shared.valueobject.*;

import java.util.ArrayList;
import java.util.List;

public class Responsavel {
    private int id;
    private Nome nome;
    private Cpf cpf;
    private Celular celular;
    private Email email;
    private String rg;
    private Telefone telefone;
    private Nome nomeSocial;
    private String genero;
    private String profissao;
//    private List<Aluno> alunos = new ArrayList<>();

    public Responsavel(){}

    public Responsavel(int id, Nome nome, Cpf cpf, Celular celular, Email email, String rg, Telefone telefone, Nome nomeSocial, String genero, String profissao){//, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.email = email;
        this.rg = rg;
        this.telefone = telefone;
        this.nomeSocial = nomeSocial;
        this.genero = genero;
        this.profissao = profissao;
//        this.alunos = alunos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nome getNome() {
        return nome;
    }

    public void setNome(Nome nome) {
        this.nome = nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Nome getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(Nome nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

//    public List<Aluno> getAlunos() {
//        return alunos;
//    }
//
//    public void setAlunos(List<Aluno> alunos) {
//        this.alunos = alunos;
//    }
}
