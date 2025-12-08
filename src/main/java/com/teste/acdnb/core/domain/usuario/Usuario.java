package com.teste.acdnb.core.domain.usuario;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.usuario.valueobject.Senha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario{
    private int id;
    private Nome nome;
    private Email email;
    private Senha senha;
    private Celular celular;
    private DataNascimento dataNascimento;
    private NomeSocial nomeSocial;
    private String genero;
    private Telefone telefone;
    private String cargo;
    private DataInclusao dataInclusao;
   // private Usuario usuarioInclusao;
    private String tokenRecuperacaoSenha;
    private LocalDateTime tokenExpiracao;

    public Usuario() {
    }

    public Usuario(int id, Nome nome, Email email, Senha senha, Celular celular, DataNascimento dataNascimento, NomeSocial nomeSocial, String genero, Telefone telefone, String cargo, DataInclusao dataInclusao,String tokenRecuperacaoSenha,
                   LocalDateTime tokenExpiracao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.celular = celular;
        this.dataNascimento = dataNascimento;
        this.nomeSocial = nomeSocial;
        this.genero = genero;
        this.telefone = telefone;
        this.cargo = cargo;
        this.dataInclusao = dataInclusao;
      //  this.usuarioInclusao = usuarioInclusao;
        this.tokenRecuperacaoSenha = tokenRecuperacaoSenha;
        this.tokenExpiracao = tokenExpiracao;
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

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Senha getSenha() {
        return senha;
    }

    public void setSenha(Senha senha) {
        this.senha = senha;
    }

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public NomeSocial getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(NomeSocial nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public DataInclusao getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(DataInclusao dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

//    public Usuario getUsuarioInclusao() {
//        return usuarioInclusao;
//    }
//    public void setUsuarioInclusao(Usuario usuarioInclusao) {
//        this.usuarioInclusao = usuarioInclusao;
//    }

    public String getTokenRecuperacaoSenha() {
        return tokenRecuperacaoSenha;
    }

    public void setTokenRecuperacaoSenha(String tokenRecuperacaoSenha) {
        this.tokenRecuperacaoSenha = tokenRecuperacaoSenha;
    }

    public LocalDateTime getTokenExpiracao() {
        return tokenExpiracao;
    }

    public void setTokenExpiracao(LocalDateTime tokenExpiracao) {
        this.tokenExpiracao = tokenExpiracao;
    }
//
//    public List<Usuario> getUsuariosCadastrados() {
//        return usuariosCadastrados;
//    }
//
//    public void setUsuariosCadastrados(List<Usuario> usuariosCadastrados) {
//        this.usuariosCadastrados = usuariosCadastrados;
//    }
}
