package com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlunoMapperUtil {
    private AlunoMapperUtil() {}

    public static List<AlunoEntity> toEntityList(List<Aluno> alunos, AlunoEntityMapper alunoEntityMapper) {
        return alunos == null ? null : alunos.stream().map(AlunoEntityMapper::toEntity).toList();
    }

    public static List<Aluno> toDomainList(List<AlunoEntity> alunos, AlunoEntityMapper alunoEntityMapper) {
        return alunos == null ? null : alunos.stream().map(AlunoEntityMapper::toDomain).toList();
    }
}