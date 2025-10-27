package com.teste.acdnb.infrastructure.dto;

import java.time.LocalDate;

public record ListaEsperaDTO(
    String nome,
    String email,
    String dataInteresse,
    String celular,
    String nomeSocial,
    String genero,
    LocalDate dataNascimento,
    String telefone,
    String dataInclusao,
    Integer usuarioInclusao,
    Integer horarioPrefId
) {
}
