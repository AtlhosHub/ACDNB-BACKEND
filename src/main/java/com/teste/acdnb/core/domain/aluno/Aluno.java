package com.teste.acdnb.core.domain.aluno;

// import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.usuario.Usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private int id;
    private Nome nome;
    private Email email;
    private DataNascimento dataNascimento;
    private Cpf cpf;
    private String rg;
    private NomeSocial nomeSocial;
    private String genero;
    private Celular celular;
    private Telefone telefone;
    private String nacionalidade;
    private String naturalidade;
    private String profissao;
    private String deficiencia;
    private boolean ativo;
    private boolean atestado;
    private boolean autorizado;
    private DataInclusao dataInclusao;
    private Endereco endereco;
    private List<Responsavel> responsaveis = new ArrayList<>();
    private Usuario usuarioInclusao;
    private List<Mensalidade> mensalidades = new ArrayList<>();

    public Aluno() {}

    public Aluno(int id, Nome nome, Email email, DataNascimento dataNascimento, Cpf cpf, String rg, NomeSocial nomeSocial, String genero, Celular celular, Telefone telefone, String nacionalidade, String naturalidade, String profissao, String deficiencia, boolean ativo, boolean atestado, boolean autorizado, DataInclusao dataInclusao, Endereco endereco, List<Responsavel> responsaveis, List<Mensalidade> mensalidades) {
        this.dataNascimento = dataNascimento;
        if(isMenor() && (responsaveis == null || responsaveis.isEmpty())){
            throw new IllegalArgumentException("Alunos menores de idade devem ter pelo menos um responsável.");
        }

        if(rg.isEmpty()){
            throw new IllegalArgumentException("O RG não pode ficar em branco");
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.nomeSocial = nomeSocial;
        this.genero = genero;
        this.celular = celular;
        this.telefone = telefone;
        this.nacionalidade = nacionalidade;
        this.naturalidade = naturalidade;
        this.profissao = profissao;
        this.deficiencia = deficiencia;
        this.ativo = ativo;
        this.atestado = atestado;
        this.autorizado = autorizado;
        this.dataInclusao = dataInclusao;
        this.endereco = endereco;
        this.responsaveis = responsaveis;
//        this.usuarioInclusao = usuarioInclusao;
        this.mensalidades = mensalidades;
    }

    public boolean isMenor(){
        return Period.between(dataNascimento.getValue(), LocalDate.now()).getYears() < 18;
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

    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtestado() {
        return atestado;
    }

    public void setAtestado(boolean atestado) {
        this.atestado = atestado;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public DataInclusao getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(DataInclusao dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Responsavel> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

//    public Usuario getUsuarioInclusao() {
//        return usuarioInclusao;
//    }
//
//    public void setUsuarioInclusao(Usuario usuarioInclusao) {
//        this.usuarioInclusao = usuarioInclusao;
//    }

    public List<Mensalidade> getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(List<Mensalidade> mensalidades) {
        this.mensalidades = mensalidades;
    }
}
