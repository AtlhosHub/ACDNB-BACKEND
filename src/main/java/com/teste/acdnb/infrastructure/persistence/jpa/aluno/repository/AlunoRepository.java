package com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository;

import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Integer> {
    boolean existsByEmailIgnoreCaseOrCpfOrRg(String email, String cpf, String rg);

    boolean existsByCpfOrRg(String cpf, String rg);

    boolean existsByEmailIgnoreCaseAndIdIsNot(String email, int id);

    boolean existsByCpfAndIdIsNot(String cpf, int id);

    boolean existsByRgAndIdIsNot(String rg, int id);

    @Query("SELECT a.nome, a.dataNascimento FROM AlunoEntity a ORDER BY a.dataNascimento")
    List<AlunoEntity> findAniversariantes();

    long countByAtivo(boolean ativo);
}
