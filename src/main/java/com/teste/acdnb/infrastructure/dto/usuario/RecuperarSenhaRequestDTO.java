package com.teste.acdnb.infrastructure.dto.usuario;

public class RecuperarSenhaRequestDTO {
    private String email;

    public RecuperarSenhaRequestDTO() {}

    public RecuperarSenhaRequestDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
