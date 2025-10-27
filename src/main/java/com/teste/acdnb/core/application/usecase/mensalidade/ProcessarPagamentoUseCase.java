package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.infrastructure.dto.mensaldiade.ComprovanteDTO;

public interface ProcessarPagamentoUseCase {
    void execute(ComprovanteDTO comprovanteDTO);
}