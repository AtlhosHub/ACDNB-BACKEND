package com.teste.acdnb.infrastructure.filter;

import java.time.LocalDate;

public record FiltroMensalidadeDTO(
        String dateFrom,
        String dateTo,
        Long idAluno
) {}
