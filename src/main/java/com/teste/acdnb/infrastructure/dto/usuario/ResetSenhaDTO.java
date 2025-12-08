package com.teste.acdnb.infrastructure.dto.usuario;

public class ResetSenhaDTO {
    private String token;
    private String novaSenha;

    // Construtores, getters e setters
    public ResetSenhaDTO() {}

    public ResetSenhaDTO(String token, String novaSenha) {
        this.token = token;
        this.novaSenha = novaSenha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
