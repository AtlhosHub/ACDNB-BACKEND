package com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository;

import com.teste.acdnb.core.domain.shared.valueobject.Email;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Integer>, JpaSpecificationExecutor<AlunoEntity> {
    boolean existsByEmailIgnoreCaseOrCpfOrRg(String email, String cpf, String rg);

    boolean existsByCpfOrRg(String cpf, String rg);

    boolean existsByEmailIgnoreCaseAndIdIsNot(String email, int id);

    boolean existsByCpfAndIdIsNot(String cpf, int id);

    boolean existsByRgAndIdIsNot(String rg, int id);

    List<AlunoEntity> findAll(@Nullable Specification<AlunoEntity> spec, @Nullable Sort sort);

    @Query("SELECT a FROM AlunoEntity a WHERE MONTH(a.dataNascimento) >= :mesAtual ORDER BY MONTH(a.dataNascimento), DAY(a.dataNascimento)")
    List<AlunoEntity> findAniversariantes(@Param("mesAtual") int mesAtual);

    long countByAtivo(boolean ativo);

    @Query("""
        SELECT DISTINCT a
        FROM AlunoEntity a
        LEFT JOIN a.responsaveis r
        WHERE LOWER(a.email) = LOWER(:email)
           OR LOWER(r.email) = LOWER(:email)
        """)
    Optional<AlunoEntity> findByEmailOrEmailResponsavel(Email email);
}