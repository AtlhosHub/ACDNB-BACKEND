package com.teste.acdnb.infrastructure.security;

import com.teste.acdnb.infrastructure.dto.EmailContatoDTO;
import com.teste.acdnb.infrastructure.dto.usuario.EmailRecuperacaoSenhaDTO;
import com.teste.acdnb.infrastructure.security.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ProdutorMensagem {
    private final RabbitTemplate rabbitTemplate;

    public ProdutorMensagem(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarAlunoCriado(Long id, String nome, String email) {
        EmailContatoDTO dto = new EmailContatoDTO(id, nome, email, "CREATE", null);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_ALUNOS,
                RabbitMQConfig.ROUTING_KEY_ALUNO,
                dto
        );
    }

    public void enviarAlunoAtualizado(Long id, String nome, String novoEmail, String emailAntigo) {
        EmailContatoDTO dto = new EmailContatoDTO(id, nome, novoEmail, "UPDATE", emailAntigo);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_ALUNOS,
                RabbitMQConfig.ROUTING_KEY_ALUNO,
                dto
        );
    }

    public void enviarEmailRecuperacaoSenha(EmailRecuperacaoSenhaDTO dto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_EMAIL,
                RabbitMQConfig.ROUTING_KEY_EMAIL_RECUPERACAO,
                dto
        );
    }
}