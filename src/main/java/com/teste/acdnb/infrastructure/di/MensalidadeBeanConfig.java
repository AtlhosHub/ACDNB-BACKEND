package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.usecase.mensalidade.ContarMensalidadeComDesconto;
import com.teste.acdnb.core.application.usecase.mensalidade.ContarMensalidadeComDescontoImpl;
import com.teste.acdnb.core.application.usecase.mensalidade.GerarRelatorioMensalidadePorMes;
import com.teste.acdnb.core.application.usecase.mensalidade.GerarRelatorioMensalidadePorMesImpl;
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
}
