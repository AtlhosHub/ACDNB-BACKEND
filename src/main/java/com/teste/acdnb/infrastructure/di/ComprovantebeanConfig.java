package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.mensalidade.ComprovanteGateway;
import com.teste.acdnb.infrastructure.gateway.mensalidade.ComprovanteRepositoryGateway;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.ComprovanteRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante.ComprovanteEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComprovantebeanConfig {
    @Bean
    public ComprovanteGateway comprovanteGateway(
            ComprovanteRepository comprovanteRepository,
            ComprovanteEntityMapper comprovanteEntityMapper
    ) {
        return new ComprovanteRepositoryGateway(comprovanteRepository, comprovanteEntityMapper);
    }

    @Bean
    public ComprovanteEntityMapper comprovanteEntityMapper() {
        return new ComprovanteEntityMapper();
    }
}
