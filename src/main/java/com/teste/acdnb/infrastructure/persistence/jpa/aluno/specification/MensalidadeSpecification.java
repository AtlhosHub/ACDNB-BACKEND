package com.teste.acdnb.infrastructure.persistence.jpa.aluno.specification;

import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.filter.AlunoFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class MensalidadeSpecification {
    private static final String STATUS = "status";
    private static final List<StatusPagamento> TODOS_STATUS = List.of(StatusPagamento.values());

    private MensalidadeSpecification() {}

    public static Specification<MensalidadeEntity> filtrarPor(AlunoFilter alunoFilter) {
        return hasStatusIn(alunoFilter.status()).and(hasDataEnvioBetween(alunoFilter.dataEnvioFrom(), alunoFilter.dataEnvioTo()));
    }

    private static Specification<MensalidadeEntity> hasStatusIn(List<String> status) {
        return ((root, query, cb) -> (status == null || status.isEmpty()) ? root.get(STATUS).in(TODOS_STATUS) : root.get(STATUS).in(status));
    }

    private static Specification<MensalidadeEntity> hasDataEnvioBetween(String dataEnvioFrom, String dataEnvioTo) {
        return ((root, query, cb) -> (dataEnvioFrom == null || dataEnvioFrom.isEmpty()) || (dataEnvioTo == null || dataEnvioTo.isEmpty()) ? cb.conjunction() : cb.between(root.get("dataEnvio"), dataEnvioFrom, dataEnvioTo));
    }
}
