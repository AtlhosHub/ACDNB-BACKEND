package com.teste.acdnb.infrastructure.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa informações detalhadas de um usuário")
public class UsuarioResponseDTO {
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
    private String email;

    @Schema(description = "Número de celular do usuário", example = "(11) 91234-5678")
    private String celular;

    @Schema(description = "Data de nascimento do usuário", example = "1990-05-15")
    private LocalDate dataNascimento;

    @Schema(description = "Nome social do usuário, caso aplicável", example = "Joana Silva")
    private String nomeSocial;

    @Schema(description = "Gênero do usuário", example = "Masculino")
    private String genero;

    @Schema(description = "Número de telefone do usuário", example = "(11) 1234-5678")
    private String telefone;

    @Schema(description = "Cargo do usuário", example = "Administrador")
    private String cargo;
}
