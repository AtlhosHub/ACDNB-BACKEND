package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade;

import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;

public class ValorMensalidadeEntityMapper {
    public static ValorMensalidadeEntity toEntity(ValorMensalidade valorMensalidade) {
        if (valorMensalidade == null) return null;
        return new ValorMensalidadeEntity(
                valorMensalidade.getId(),
                valorMensalidade.getValor(),
                valorMensalidade.isManualFlag(),
                valorMensalidade.isDesconto(),
                valorMensalidade.getDataInclusao()
        );
    }

    public static ValorMensalidade toDomain(ValorMensalidadeEntity valorMensalidadeEntity) {
        if (valorMensalidadeEntity == null) return null;
        return new ValorMensalidade(
                valorMensalidadeEntity.getId(),
                valorMensalidadeEntity.getValor(),
                valorMensalidadeEntity.isManualFlag(),
                valorMensalidadeEntity.isDesconto()
        );
    }
}
