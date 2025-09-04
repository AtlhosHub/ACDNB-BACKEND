package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensalidadeRepository extends JpaRepository<MensalidadeEntity, Integer> {
    long countByAlunoAndStatusPagamentoIn(Aluno aluno, List<StatusPagamento> status);
}
