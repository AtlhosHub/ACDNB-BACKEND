package com.teste.acdnb.core.application.gateway.mensalidade;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.application.usecase.mensalidade.dto.RelatorioMensalidade;
import com.teste.acdnb.infrastructure.filter.AlunoFilter;

import java.util.List;
import java.util.Optional;

public interface MensalidadeGateway {
    long contarMensalidadePendentesOuAtrasadas(Aluno aluno);
    int contarMensalidadeComDesconto();
    Optional<Mensalidade> buscarMensalidadePorId(Long id);

    List<RelatorioMensalidade> gerarRelatorioMensalidadePorMes();

    Mensalidade salvar(Mensalidade mensalidade);
    void salvarTodas(List<Mensalidade> mensalidade);
    List<Mensalidade> buscarMensalidadesPendentesOuAtrasadasPorAluno(Aluno aluno);
    List<Mensalidade> listarMensalidadesFiltro(AlunoFilter filter);
}
