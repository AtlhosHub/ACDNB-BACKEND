package com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository;

import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Integer> {
}
