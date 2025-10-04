package com.teste.acdnb.infrastructure.security.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_ALUNO_EMAIL = "aluno.email";

    @Bean
    public Queue prodAlunoEmail(){
        return new Queue(QUEUE_ALUNO_EMAIL, true);
    }

    @Bean
    public Jackson2JsonMessageConverter converterMensagem(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converterMensagem());
        return template;
    }
}
