package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.infrastructure.dto.PagamentoManualDTO;

public interface AtualizarMensalidade {
    public Mensalidade execute(Long id, PagamentoManualDTO dto);
}
