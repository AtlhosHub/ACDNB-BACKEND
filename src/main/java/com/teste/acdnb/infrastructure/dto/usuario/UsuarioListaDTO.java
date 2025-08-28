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
@Schema(description = "DTO que representa uma listagem de usuários")
public class UsuarioListaDTO {

    @Schema(description = "Identificador único do usuário", example = "1")
    private int id;

    @Schema(description = "Nome do usuário", example = "João Silva")
    private String nome;
}
