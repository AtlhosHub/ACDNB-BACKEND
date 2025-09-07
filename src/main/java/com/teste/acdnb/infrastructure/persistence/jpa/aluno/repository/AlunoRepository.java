package com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository;

import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Integer> {
    boolean existsByEmailIgnoreCaseOrCpfOrRg(String email, String cpf, String rg);

    boolean existsByCpfOrRg(String cpf, String rg);
}
