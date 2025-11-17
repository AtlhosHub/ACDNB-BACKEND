package com.teste.acdnb.infrastructure.security.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // ---- ALUNOS ----
    public static final String EXCHANGE_ALUNOS = "exchange-alunos";
    public static final String ROUTING_KEY_ALUNO = "aluno.cadastrado";
    public static final String QUEUE_ALUNO_CADASTRADO = "fila-aluno-cadastrado";

    // ---- COMPROVANTES ----
    public static final String EXCHANGE_COMPROVANTES = "exchange-comprovantes";
    public static final String ROUTING_KEY_COMPROVANTE = "comprovante.processado";
    public static final String QUEUE_COMPROVANTE_PROCESSADO = "fila-comprovante-processado";

    // ---- RETORNO DE PAGAMENTO ----
    public static final String EXCHANGE_PAGAMENTO_RETORNO = "exchange-pagamento-retorno";
    public static final String ROUTING_KEY_PAGAMENTO_RETORNO = "pagamento.retorno";
    public static final String QUEUE_PAGAMENTO_RETORNO = "fila-pagamento-retorno";

    @Bean
    public TopicExchange exchangeAlunos() {
        return new TopicExchange(EXCHANGE_ALUNOS);
    }

    @Bean
    public Queue filaAlunoCadastrado() {
        return new Queue(QUEUE_ALUNO_CADASTRADO, true);
    }

    @Bean
    public Binding bindingAluno() {
        return BindingBuilder
                .bind(filaAlunoCadastrado())
                .to(exchangeAlunos())
                .with(ROUTING_KEY_ALUNO);
    }

    @Bean
    public TopicExchange exchangeComprovantes() {
        return new TopicExchange(EXCHANGE_COMPROVANTES);
    }

    @Bean
    public Queue filaComprovanteProcessado() {
        return new Queue(QUEUE_COMPROVANTE_PROCESSADO, true);
    }

    @Bean
    public Binding bindingComprovante() {
        return BindingBuilder
                .bind(filaComprovanteProcessado())
                .to(exchangeComprovantes())
                .with(ROUTING_KEY_COMPROVANTE);
    }

    @Bean
    public Jackson2JsonMessageConverter converterMensagem() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converterMensagem());
        return template;
    }

    @Bean
    public TopicExchange exchangePagamentoRetorno() {
        return new TopicExchange(EXCHANGE_PAGAMENTO_RETORNO);
    }

    @Bean
    public Queue filaPagamentoRetorno() {
        return new Queue(QUEUE_PAGAMENTO_RETORNO, true);
    }

    @Bean
    public Binding bindingPagamentoRetorno() {
        return BindingBuilder
                .bind(filaPagamentoRetorno())
                .to(exchangePagamentoRetorno())
                .with(ROUTING_KEY_PAGAMENTO_RETORNO);
    }

}
