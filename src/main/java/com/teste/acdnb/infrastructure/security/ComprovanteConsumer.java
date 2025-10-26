package com.teste.acdnb.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.valueobject.ValoresComprovante;
import com.teste.acdnb.infrastructure.dto.mensaldiade.ComprovanteDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.ComprovanteRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante.ComprovanteEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ComprovanteConsumer {

    private final ComprovanteRepository comprovanteRepository;
    private final ObjectMapper objectMapper;

    public ComprovanteConsumer(ComprovanteRepository comprovanteRepository, ObjectMapper objectMapper) {
        this.comprovanteRepository = comprovanteRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "#{@comprovanteQueue.name}")
    public void consumirComprovante(String mensagemJson) {
        try {
            System.out.println("📨 Comprovante recebido: " + mensagemJson);

            ComprovanteDTO comprovanteDTO = objectMapper.readValue(mensagemJson, ComprovanteDTO.class);

            ComprovanteEntity comprovanteEntity = converterParaEntity(comprovanteDTO);

            ComprovanteEntity saved = comprovanteRepository.save(comprovanteEntity);

            System.out.println("✅ Comprovante salvo com sucesso! ID: " + saved.getId());
            System.out.println("📋 Detalhes: " + saved.getNomeRemetente() + " | " +
                    saved.getBancoOrigem() + " → " + saved.getBancoDestino());

        } catch (Exception e) {
            System.err.println("❌ Erro ao processar comprovante: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private ComprovanteEntity converterParaEntity(ComprovanteDTO dto) {
        ComprovanteEntity entity = new ComprovanteEntity();

        entity.setNomeRemetente(dto.nomeRemetente());
        entity.setContaDestino(dto.nomeDestinatario());
        entity.setDataEnvio(dto.dataHora());
        entity.setBancoOrigem(dto.bancoOrigem());
        entity.setBancoDestino(dto.bancoDestino());

        BigDecimal valor = new BigDecimal(dto.valor());

        ValoresComprovante valores = new ValoresComprovante();
        valores.setValorCheio(valor);

        entity.setValores(valores);

        return entity;
    }
}