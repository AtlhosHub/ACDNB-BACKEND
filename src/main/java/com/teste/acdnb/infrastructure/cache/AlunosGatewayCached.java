package com.teste.acdnb.infrastructure.cache;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoMapperUtil;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.AlunoRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.specification.AlunoSpecification;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public abstract class AlunosGatewayCached implements AlunoGateway {
    private final AlunoRepository alunoRepository;
    private final AlunoEntityMapper alunoEntityMapper;

    protected AlunosGatewayCached(AlunoRepository alunoRepository, AlunoEntityMapper alunoEntityMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoEntityMapper = alunoEntityMapper;
    }

    @Override
    @Cacheable("listarAlunosFiltro")
    public List<Aluno> listarAlunosFiltro(ListarAlunosMensalidadeFilter filter) {
        Specification<AlunoEntity> spec = AlunoSpecification.filtrarPor(filter);

        List<AlunoEntity> lista = alunoRepository.findAll(
                spec,
                Sort.by(Sort.Order.asc("nome").ignoreCase())
        );

        return AlunoMapperUtil.toDomainList(lista, alunoEntityMapper);
    }
}
