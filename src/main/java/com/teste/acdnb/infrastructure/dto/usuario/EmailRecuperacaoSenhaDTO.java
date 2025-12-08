package com.teste.acdnb.infrastructure.dto.usuario;

public class EmailRecuperacaoSenhaDTO {
    private String email;
    private String nome;
    private String token;
    private String linkRecuperacao;

    // Construtor padrão
    public EmailRecuperacaoSenhaDTO() {}

    // Construtor com parâmetros
    public EmailRecuperacaoSenhaDTO(String email, String nome, String token, String linkRecuperacao) {
        this.email = email;
        this.nome = nome;
        this.token = token;
        this.linkRecuperacao = linkRecuperacao;
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLinkRecuperacao() {
        return linkRecuperacao;
    }

    public void setLinkRecuperacao(String linkRecuperacao) {
        this.linkRecuperacao = linkRecuperacao;
    }
}