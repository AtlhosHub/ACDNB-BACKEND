package com.teste.acdnb.infrastructure.gateway.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade.ValorMensalidadeEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade.ValorMensalidadeEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade.ValorMensalidadeRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ValorMensalidadeRepositoryGateway implements ValorMensalidadeGateway {
    private final ValorMensalidadeRepository valorMensalidadeRepository;

    public ValorMensalidadeRepositoryGateway(ValorMensalidadeRepository valorMensalidadeRepository) {
        this.valorMensalidadeRepository = valorMensalidadeRepository;
    }

    @Override
    public Optional<ValorMensalidade> buscarValorMensalidadePorValorEManualFlag(BigDecimal valor, boolean manualFlag) {
        List<ValorMensalidadeEntity> valorMensalidade = valorMensalidadeRepository.findByValorAndManualFlag(valor, manualFlag);
        return valorMensalidade.isEmpty() ? Optional.empty() : Optional.ofNullable(ValorMensalidadeEntityMapper.toDomain(valorMensalidade.get(0)));
    }

    @Override
    public ValorMensalidade adicionarValorMensalidade(ValorMensalidade valorMensalidade) {
        ValorMensalidadeEntity valorMensalidadeEntity = ValorMensalidadeEntityMapper.toEntity(valorMensalidade);
        ValorMensalidadeEntity novoValorMensalidade = valorMensalidadeRepository.save(valorMensalidadeEntity);

        return ValorMensalidadeEntityMapper.toDomain(novoValorMensalidade);
    }

    @Override
    public ValorMensalidade buscarValorMensalidadeAtual() {
        ValorMensalidadeEntity valorMensalidadeEntity = valorMensalidadeRepository.findFirstByManualFlagFalseAndDescontoFalseOrderByDataInclusaoDesc();
        return ValorMensalidadeEntityMapper.toDomain(valorMensalidadeEntity);
    }
}
