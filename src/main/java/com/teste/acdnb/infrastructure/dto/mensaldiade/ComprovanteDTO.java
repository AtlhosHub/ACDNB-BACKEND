package com.teste.acdnb.infrastructure.dto.mensaldiade;

import java.time.LocalDateTime;

public record ComprovanteDTO(
        String nomeRemetente,
        String nomeDestinatario,
        String valor,
        LocalDateTime dataHora,
        String tipo,
        String bancoOrigem,
        String bancoDestino
) {}
