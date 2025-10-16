package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.application.usecase.mensalidade.*;
import com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade.BuscarValorMensalidadeAtual;
import com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade.BuscarValorMensalidadeAtualImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MensalidadeBeanConfig {
    @Bean
    public GerarRelatorioMensalidadePorMes gerarRelatorioMensalidadePorMes(MensalidadeGateway mensalidadeGateway) {
        return new GerarRelatorioMensalidadePorMesImpl(mensalidadeGateway);
    };

    @Bean
    public ContarMensalidadeComDesconto contarMensalidadeComDesconto(MensalidadeGateway mensalidadeGateway) {
        return new ContarMensalidadeComDescontoImpl(mensalidadeGateway);
    }

    @Bean
    public AtualizarMensalidade atualizarMensalidade(MensalidadeGateway mensalidadeGateway, ValorMensalidadeGateway valorMensalidadeGateway) {
        return new AtualizarMensalidadeImpl(valorMensalidadeGateway, mensalidadeGateway);
    }

    @Bean
    public BuscarValorMensalidadeAtual buscarValorMensalidadeAtual(ValorMensalidadeGateway valorMensalidadeGateway) {
        return new BuscarValorMensalidadeAtualImpl(valorMensalidadeGateway);
    }
}
