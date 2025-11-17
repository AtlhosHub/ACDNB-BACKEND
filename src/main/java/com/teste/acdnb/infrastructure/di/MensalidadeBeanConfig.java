package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ComprovanteGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.application.usecase.mensalidade.*;
import com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade.AdicionarValorMensalidade;
import com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade.AdicionarValorMensalidadeImpl;
import com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade.BuscarValorMensalidadeAtual;
import com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade.BuscarValorMensalidadeAtualImpl;
import com.teste.acdnb.infrastructure.security.PagamentoRetornoProdutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class MensalidadeBeanConfig {
    @Bean
    public GerarRelatorioMensalidadePorMes gerarRelatorioMensalidadePorMes(MensalidadeGateway mensalidadeGateway) {
        return new GerarRelatorioMensalidadePorMesImpl(mensalidadeGateway);
    }

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

    @Bean
    public AdicionarValorMensalidade adicionarValorMensalidade(ValorMensalidadeGateway valorMensalidadeGateway){
        return new AdicionarValorMensalidadeImpl(valorMensalidadeGateway);
    }

    @Value("${app.rabbitmq.queue.comprovante:fila-comprovante-processado}")
    private String queueName;

    @Bean
    public Queue comprovanteQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public ProcessarPagamentoUseCase processarPagamentoUseCase(AlunoGateway alunoGateway,
                                                               MensalidadeGateway mensalidadeGateway,
                                                               ComprovanteGateway comprovanteGateway,
                                                               PagamentoRetornoProdutor pagamentoRetornoProdutor) {
        return new ProcessarPagamentoUseCaseImpl(alunoGateway, mensalidadeGateway, comprovanteGateway, pagamentoRetornoProdutor);
    }
}
