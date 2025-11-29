package com.teste.acdnb.infrastructure.dto;

public record EmailContatoDTO (
        Long id,
        String nome,
        String email,
        String operacao,
        String emailAntigo){
}