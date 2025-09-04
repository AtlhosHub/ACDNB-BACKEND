package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade;

import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante.ComprovanteEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade.ValorMensalidadeEntityMapper;

import java.util.Optional;

public class MensalidadeEntityMapper {
    public static MensalidadeEntity toEntity(Mensalidade mensalidade) {
        if (mensalidade == null) return null;
        return new MensalidadeEntity(
                mensalidade.getId(),
                mensalidade.getAluno(),
                mensalidade.getDataVencimento(),
                mensalidade.getDataPagamento(),
                mensalidade.getStatusPagamento(),
                mensalidade.isAlteracaoAutomatica(),
                ValorMensalidadeEntityMapper.toEntity(mensalidade.getValor()),
                mensalidade.getFormaPagamento(),
                ComprovanteEntityMapper.toEntity(mensalidade.getComprovante())
        );
    }

    public static Mensalidade toDomain(MensalidadeEntity mensalidadeEntity) {
        if (mensalidadeEntity == null) return null;
        return new Mensalidade(
                mensalidadeEntity.getId(),
                mensalidadeEntity.getFormaPagamento(),
                ValorMensalidadeEntityMapper.toDomain(mensalidadeEntity.getValor()),
                mensalidadeEntity.isAlteracaoAutomatica(),
                mensalidadeEntity.getStatusPagamento(),
                mensalidadeEntity.getDataPagamento(),
                mensalidadeEntity.getDataVencimento(),
                mensalidadeEntity.getAluno(),
                Optional.of(ComprovanteEntityMapper.toDomain(mensalidadeEntity.getComprovante()))
        );
    }
}
