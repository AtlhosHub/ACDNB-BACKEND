package com.teste.acdnb.infrastructure.gateway.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeRepository;
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
    private final MensalidadeGateway mensalidadeGateway;

    public ValorMensalidadeRepositoryGateway(ValorMensalidadeRepository valorMensalidadeRepository, MensalidadeGateway mensalidadeGateway) {
        this.valorMensalidadeRepository = valorMensalidadeRepository;
        this.mensalidadeGateway = mensalidadeGateway;
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

        ValorMensalidade valor = ValorMensalidadeEntityMapper.toDomain(novoValorMensalidade);

        List<Mensalidade> mensalidadesFuturas = mensalidadeGateway.buscarTodasMensalidadesFutura();
        for(Mensalidade m : mensalidadesFuturas) {
            if(m.getStatusPagamento() != StatusPagamento.PAGO) {
                m.setValor(valor);
            }
        }
        mensalidadeGateway.salvarTodas(mensalidadesFuturas);

        return valor;
    }

    @Override
    public ValorMensalidade buscarValorMensalidadeAtual() {
        ValorMensalidadeEntity valorMensalidadeEntity = valorMensalidadeRepository.findFirstByManualFlagFalseAndDescontoFalseOrderByDataInclusaoDesc();
        return ValorMensalidadeEntityMapper.toDomain(valorMensalidadeEntity);
    }
}
