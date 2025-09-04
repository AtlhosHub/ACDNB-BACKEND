package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.MensalidadeGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeRepository;

import java.util.List;

public class MensalidadeRepositoryGateway implements MensalidadeGateway {
    private final MensalidadeRepository mensalidadeRepository;
    private final MensalidadeEntityMapper mensalidadeEntityMapper;

    public MensalidadeRepositoryGateway(MensalidadeRepository mensalidadeRepository, MensalidadeEntityMapper mensalidadeEntityMapper) {
        this.mensalidadeRepository = mensalidadeRepository;
        this.mensalidadeEntityMapper = mensalidadeEntityMapper;
    }

    @Override
    public void salvarTodasMensalidades(List<Mensalidade> mensalidades) {
        for(int i = 0; i <= mensalidades.size(); i++) {
            MensalidadeEntity mensalidadeEntity = MensalidadeEntityMapper.toEntity(mensalidades.get(0));
            MensalidadeEntity novaMensalidade = mensalidadeRepository.save(mensalidadeEntity);
        }
    }

    @Override
    public long contarPendentesOuAtrasadas(Aluno aluno) {
        return mensalidadeRepository
                .countByAlunoAndStatusPagamentoIn(
                        aluno,
                        List.of(StatusPagamento.PENDENTE, StatusPagamento.ATRASADO)
                );
    }
}
