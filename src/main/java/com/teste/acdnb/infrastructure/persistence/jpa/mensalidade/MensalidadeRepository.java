package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade;

import com.teste.acdnb.core.application.usecase.mensalidade.dto.RelatorioMensalidade;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MensalidadeRepository extends JpaRepository<MensalidadeEntity, Integer> {
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
    long countByAlunoAndStatusPagamentoIn(Aluno aluno, List<StatusPagamento> status);
}
