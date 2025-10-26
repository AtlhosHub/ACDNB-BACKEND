package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade;

import com.teste.acdnb.core.application.usecase.mensalidade.dto.RelatorioMensalidade;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MensalidadeRepository extends JpaRepository<MensalidadeEntity, Integer>{
    List<Mensalidade> findByStatusPagamentoAndDataVencimentoBefore(StatusPagamento status, LocalDate data);

    @Query("""
        SELECT new com.teste.acdnb.core.application.usecase.mensalidade.dto.RelatorioMensalidade(
            MONTH(m.dataVencimento),
            SUM(CASE WHEN m.statusPagamento = 'ATRASADO' THEN 1 ELSE 0 END),
            SUM(CASE WHEN m.statusPagamento = 'PAGO' AND v.desconto = false THEN 1 ELSE 0 END),
            SUM(CASE WHEN m.statusPagamento = 'PAGO' AND v.desconto = true THEN 1 ELSE 0 END)
        )
        FROM MensalidadeEntity m
        JOIN m.valor v
        WHERE m.statusPagamento IN ('PAGO', 'ATRASADO') AND YEAR(m.dataVencimento) = YEAR(CURRENT_DATE)
        GROUP BY MONTH(m.dataVencimento)
        ORDER BY MONTH(m.dataVencimento)
    """)
    List<RelatorioMensalidade> gerarRelatorioMensalidadePorMes();

    @Query("SELECT COUNT(m) FROM MensalidadeEntity m JOIN m.valor v WHERE m.statusPagamento = 'PAGO' AND YEAR(m.dataPagamento) = YEAR(CURRENT_DATE)  AND v.desconto = true")
    int countMensalidadeComDesconto();

    long countByAlunoAndStatusPagamentoIn(AlunoEntity aluno, List<StatusPagamento> status);

    List<MensalidadeEntity> findAll(@Nullable Specification<MensalidadeEntity> spec, Sort sort);

    @Query("""
        SELECT COUNT(DISTINCT m.aluno.id)
        FROM MensalidadeEntity m
        JOIN m.valor v
        WHERE (:status IS NULL OR m.statusPagamento IN :status)
          AND (:nomeAluno IS NULL OR LOWER(m.aluno.nome) LIKE LOWER(CONCAT('%', :nomeAluno, '%')))
          AND (:dataFrom IS NULL OR m.dataVencimento >= :dataFrom)
          AND (:dataTo IS NULL OR m.dataVencimento <= :dataTo)
    """)
    long countAlunosComMensalidade(
            @Param("status") List<String> status,
            @Param("nomeAluno") String nomeAluno,
            @Param("dataFrom") LocalDate dataFrom,
            @Param("dataTo") LocalDate dataTo
    );
}
