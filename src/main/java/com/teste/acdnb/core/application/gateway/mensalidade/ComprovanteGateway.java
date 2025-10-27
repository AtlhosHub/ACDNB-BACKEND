package com.teste.acdnb.core.application.gateway.mensalidade;

import com.teste.acdnb.core.domain.mensalidade.entities.Comprovante;

public interface ComprovanteGateway {
    Comprovante salvar(Comprovante comprovante);
}
