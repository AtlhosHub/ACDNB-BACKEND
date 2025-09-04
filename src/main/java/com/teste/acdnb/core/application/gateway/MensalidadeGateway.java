package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;

import java.util.List;

public interface MensalidadeGateway {
    void salvarTodasMensalidades(List<Mensalidade> mensalidade);
    long contarPendentesOuAtrasadas(Aluno aluno);
}
