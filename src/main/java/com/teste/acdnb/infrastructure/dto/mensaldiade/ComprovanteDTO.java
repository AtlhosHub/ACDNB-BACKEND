package com.teste.acdnb.infrastructure.dto.mensaldiade;

import com.teste.acdnb.core.domain.shared.valueobject.Email;

import java.time.LocalDateTime;

public record ComprovanteDTO(
        String nomeRemetente,
        String nomeDestinatario,
        String valor,
        LocalDateTime dataHora,
        String tipo,
        String bancoOrigem,
        String bancoDestino,
        Email emailDestinatario
) {}
