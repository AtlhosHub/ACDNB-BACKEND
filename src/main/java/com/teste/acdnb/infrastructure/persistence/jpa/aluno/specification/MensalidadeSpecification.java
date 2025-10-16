package com.teste.acdnb.infrastructure.persistence.jpa.aluno.specification;

import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.filter.AlunoFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class MensalidadeSpecification {
    private static final String STATUS = "statusPagamento";
    private static final List<StatusPagamento> TODOS_STATUS = List.of(StatusPagamento.values());
    private static final String DATA_VENCIMENTO = "dataVencimento";

    private MensalidadeSpecification() {}

    public static Specification<MensalidadeEntity> filtrarPor(AlunoFilter alunoFilter) {
        return hasStatusIn(alunoFilter.status()).and(
                hasDataEnvioFrom(alunoFilter.dataEnvioFrom())
        ).and(
                hasDataEnvioTo(alunoFilter.dataEnvioTo())
        );
    }

    private static Specification<MensalidadeEntity> hasStatusIn(List<String> status) {
        return ((root, query, cb) -> (status == null || status.isEmpty()) ? root.get(STATUS).in(TODOS_STATUS) : root.get(STATUS).in(status));
    }

    private static Specification<MensalidadeEntity> hasDataEnvioFrom(String dataEnvioFrom) {
        return ((root, query, cb) -> (dataEnvioFrom == null || dataEnvioFrom.isEmpty()) ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get(DATA_VENCIMENTO), LocalDate.parse(dataEnvioFrom)));
    }

    private static Specification<MensalidadeEntity> hasDataEnvioTo(String dataEnvioTo) {
        return ((root, query, cb) -> (dataEnvioTo == null || dataEnvioTo.isEmpty()) ? cb.conjunction() : cb.lessThanOrEqualTo(root.get(DATA_VENCIMENTO), LocalDate.parse(dataEnvioTo)));
    }
}
