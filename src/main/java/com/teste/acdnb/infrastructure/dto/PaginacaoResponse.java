package com.teste.acdnb.infrastructure.dto;

import java.util.List;

public record PaginacaoResponse<T>(
        List<T> content,
        int offset,
        int limit,
        long total
) {}
