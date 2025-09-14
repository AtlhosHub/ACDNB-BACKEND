package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante;

import com.teste.acdnb.core.domain.mensalidade.entities.Comprovante;
import com.teste.acdnb.core.domain.shared.valueobject.Nome;

public class ComprovanteEntityMapper {
    public static ComprovanteEntity toEntity(Comprovante comprovante) {
        if (comprovante == null) return null;
        return new ComprovanteEntity(
                comprovante.getId(),
                comprovante.getNomeRemetente().getValue(),
                comprovante.getBancoOrigem(),
                comprovante.getValores(),
                comprovante.getDataEnvio(),
                comprovante.getContaDestino(),
                comprovante.getBancoDestino()
        );
    }

    public static Comprovante toDomain(ComprovanteEntity comprovanteEntity) {
        if (comprovanteEntity == null) return null;
        return new Comprovante(
                comprovanteEntity.getId(),
                Nome.of(comprovanteEntity.getNomeRemetente()),
                comprovanteEntity.getBancoOrigem(),
                comprovanteEntity.getValores(),
                comprovanteEntity.getDataEnvio(),
                comprovanteEntity.getContaDestino(),
                comprovanteEntity.getBancoDestino()
        );
    }
}
