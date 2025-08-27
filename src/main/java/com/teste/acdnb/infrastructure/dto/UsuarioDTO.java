package com.teste.acdnb.infrastructure.dto;

import java.time.LocalDateTime;

public record UsuarioDTO (
        String nome,
        String email,
        String celular,
        LocalDateTime dataNascimento,
        String nomeSocial,
        String genero,
        String senha,
        String telefone,
        String cargo,
        LocalDateTime dataInclusao
){
}
