package com.teste.acdnb.core.domain.listaEspera;

import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.usuario.Usuario;

public class ListaEspera {
    private int id;
    private Nome nome;
    private Email email;
    private DataInclusao dataInteresse;
    private Celular celular;
    private Nome nomeSocial;
    private String genero;
    private DataInclusao dataNascimento;
    private Telefone telefone;
    private DataInclusao dataInclusao;
    private Usuario usuarioInclusao;
    private HorarioPreferencia horarioPref;

    public ListaEspera() {
    }

    public ListaEspera(int id, Nome nome, Email email, DataInclusao dataInteresse, Celular celular, Nome nomeSocial, String genero, DataInclusao dataNascimento, Telefone telefone, DataInclusao dataInclusao, Usuario usuarioInclusao, HorarioPreferencia horarioPref) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataInteresse = dataInteresse;
        this.celular = celular;
        this.nomeSocial = nomeSocial;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.dataInclusao = dataInclusao;
        this.usuarioInclusao = usuarioInclusao;
        this.horarioPref = horarioPref;
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


    public com.teste.acdnb.core.domain.shared.valueobject.Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public DataInclusao getDataInteresse() {
        return dataInteresse;
    }

    public void setDataInteresse(DataInclusao dataInteresse) {
        this.dataInteresse = dataInteresse;
    }

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
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

    public DataInclusao getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataInclusao dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public DataInclusao getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(DataInclusao dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Usuario getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public void setUsuarioInclusao(Usuario usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public HorarioPreferencia getHorarioPref() {
        return horarioPref;
    }

    public void setHorarioPref(HorarioPreferencia horarioPref) {
        this.horarioPref = horarioPref;
    }
}
