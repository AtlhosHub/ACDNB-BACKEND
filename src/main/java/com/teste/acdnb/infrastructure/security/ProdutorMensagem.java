package com.teste.acdnb.infrastructure.security;

import com.teste.acdnb.infrastructure.dto.EmailContatoDTO;
import com.teste.acdnb.infrastructure.security.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ProdutorMensagem{
    private final RabbitTemplate rabbitTemplate;
    public ProdutorMensagem(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void ProduzirMensagem(EmailContatoDTO emailContatoDTO) {
        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_ALUNOS,
                    RabbitMQConfig.ROUTING_KEY_ALUNO,
                    emailContatoDTO
            );
            System.out.println("📨 Mensagem enviada para RabbitMQ: {}"+ emailContatoDTO);
        } catch (Exception e) {
            System.out.println("❌ Erro ao enviar mensagem para RabbitMQ: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
