package com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade;

import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.infrastructure.dto.mensaldiade.NovoValorMensalidadeDTO;

public interface AdicionarValorMensalidade {
    ValorMensalidade execute(NovoValorMensalidadeDTO novoValorMensalidadeDTO);
}
