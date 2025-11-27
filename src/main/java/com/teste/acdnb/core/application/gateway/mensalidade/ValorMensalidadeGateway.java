package com.teste.acdnb.core.application.gateway.mensalidade;

import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;

import java.math.BigDecimal;
import java.util.Optional;

public interface ValorMensalidadeGateway {
    Optional<ValorMensalidade> buscarValorMensalidadePorValorEManualFlag(BigDecimal valor, boolean manualFlag);
    ValorMensalidade adicionarValorMensalidade(ValorMensalidade valorMensalidade);
    ValorMensalidade buscarValorMensalidadeAtual();
}
