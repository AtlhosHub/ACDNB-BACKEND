package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade;

import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntity;

public class ValorMensalidadeEntityMapper {
    public static ValorMensalidadeEntity toEntity(ValorMensalidade valorMensalidade) {
        if (valorMensalidade == null) return null;
        return new ValorMensalidadeEntity(
                valorMensalidade.getId(),
                valorMensalidade.getValor(),
                valorMensalidade.isManual(),
                valorMensalidade.isDesconto(),
                valorMensalidade.getDataInclusao()
        );
    }

    public static ValorMensalidade toDomain(ValorMensalidadeEntity valorMensalidadeEntity) {
        if (valorMensalidadeEntity == null) return null;
        return new ValorMensalidade(
                valorMensalidadeEntity.getId(),
                valorMensalidadeEntity.getValor(),
                valorMensalidadeEntity.isManual(),
                valorMensalidadeEntity.isDesconto()
        );
    }
}
