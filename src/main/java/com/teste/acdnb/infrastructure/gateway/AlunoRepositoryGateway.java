package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.AlunoRepository;
import org.springframework.stereotype.Component;

@Component
public class AlunoRepositoryGateway implements AlunoGateway {
    private final AlunoRepository alunoRepository;
    private final AlunoEntityMapper alunoEntityMapper;

    public AlunoRepositoryGateway(AlunoRepository alunoRepository, AlunoEntityMapper alunoEntityMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoEntityMapper = alunoEntityMapper;
    }

    @Override
    public Aluno adicionarAluno(Aluno aluno) {
        AlunoEntity alunoEntity = alunoEntityMapper.toEntity(aluno);
        AlunoEntity novoAluno = alunoRepository.save(alunoEntity);

        return alunoEntityMapper.toDomain(novoAluno);
    }
}
