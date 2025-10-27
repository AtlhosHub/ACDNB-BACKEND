package com.teste.acdnb.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.acdnb.core.application.usecase.mensalidade.ProcessarPagamentoUseCase;
import com.teste.acdnb.infrastructure.dto.mensaldiade.ComprovanteDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ComprovanteConsumer {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;
    private final ObjectMapper objectMapper;

    public ComprovanteConsumer(ProcessarPagamentoUseCase processarPagamentoUseCase, ObjectMapper objectMapper) {
        this.processarPagamentoUseCase = processarPagamentoUseCase;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "#{@comprovanteQueue.name}")
    public void consumirComprovante(String mensagemJson) {
        try {
            System.out.println("📨 Comprovante recebido: " + mensagemJson);

            ComprovanteDTO comprovanteDTO = objectMapper.readValue(mensagemJson, ComprovanteDTO.class);

            processarPagamentoUseCase.execute(comprovanteDTO);

            System.out.println("✅ Pagamento processado com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao processar comprovante: " + e.getMessage());
            e.printStackTrace();
        }
    }
}