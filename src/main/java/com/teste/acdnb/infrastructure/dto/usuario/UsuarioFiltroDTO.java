package com.teste.acdnb.infrastructure.dto.usuario;

public record UsuarioFiltroDTO(
        String nome,
        int offset,
        int limit
) {
}
