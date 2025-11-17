package com.teste.acdnb.infrastructure.security;

import com.teste.acdnb.infrastructure.dto.RespostaPagementoDTO;
import com.teste.acdnb.infrastructure.security.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PagamentoRetornoProdutor {

    private final RabbitTemplate rabbitTemplate;

    public PagamentoRetornoProdutor(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(RespostaPagementoDTO dto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_PAGAMENTO_RETORNO,
                RabbitMQConfig.ROUTING_KEY_PAGAMENTO_RETORNO,
                dto
        );
    }
}

