package com.teste.acdnb.infrastructure.dto.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String celular,
        LocalDate dataNascimento,
        String nomeSocial,
        String genero,
        String senha,
        String telefone,
        String cargo,
        LocalDateTime dataInclusao
){
}
