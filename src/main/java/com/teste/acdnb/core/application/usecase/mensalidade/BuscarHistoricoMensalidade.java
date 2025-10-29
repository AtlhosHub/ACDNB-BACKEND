package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.infrastructure.filter.FiltroMensalidadeDTO;

import java.util.List;

public interface BuscarHistoricoMensalidade {
    public List<Mensalidade> execute(FiltroMensalidadeDTO payload);
}
