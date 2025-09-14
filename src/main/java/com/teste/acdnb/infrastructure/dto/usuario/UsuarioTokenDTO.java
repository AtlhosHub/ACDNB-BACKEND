package com.teste.acdnb.infrastructure.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa um token de autenticação de usuário")
public class UsuarioTokenDTO {
    @Schema(description = "Identificador único do usuário", example = "1")
    private int id;

    @Schema(description = "Nome do usuário", example = "João Silva")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
    private String email;

    @Schema(description = "Token de autenticação do usuário", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Usuário que incluiu este usuário no sistema")
    private Integer usuarioInclusao;
}
