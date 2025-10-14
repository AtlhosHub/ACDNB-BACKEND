package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade;

import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante.ComprovanteEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade.ValorMensalidadeEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MensalidadeEntityMapper {
    public static MensalidadeEntity toEntity(Mensalidade mensalidade) {
        if (mensalidade == null) return null;
        return new MensalidadeEntity(
                mensalidade.getId(),
                AlunoEntityMapper.toEntity(mensalidade.getAluno()),
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
                Optional.ofNullable(mensalidadeEntity.getFormaPagamento()),
                ValorMensalidadeEntityMapper.toDomain(mensalidadeEntity.getValor()),
                mensalidadeEntity.isAlteracaoAutomatica(),
                mensalidadeEntity.getStatusPagamento(),
                mensalidadeEntity.getDataPagamento(),
                mensalidadeEntity.getDataVencimento(),
                AlunoEntityMapper.toDomain(mensalidadeEntity.getAluno()),
                Optional.ofNullable(ComprovanteEntityMapper.toDomain(mensalidadeEntity.getComprovante()))
        );
    }

    public static List<MensalidadeEntity> toEntityList(List<Mensalidade> mensalidades) {
        return mensalidades.stream().map(MensalidadeEntityMapper::toEntity).toList();
    }

    public static List<Mensalidade> toDomainList(List<MensalidadeEntity> mensalidades) {
        return mensalidades.stream().map(MensalidadeEntityMapper::toDomain).toList();
    }
}
