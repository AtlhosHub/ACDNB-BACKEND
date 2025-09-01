package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.application.usecase.aluno.AdicionarAlunoUseCase;
import com.teste.acdnb.core.application.usecase.aluno.AdicionarAlunoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlunoBeanConfig {
    @Bean
    public AdicionarAlunoUseCase adicionarAlunoUseCase(AlunoGateway alunoGateway) {
        return new AdicionarAlunoUseCaseImpl(alunoGateway);
    }
}
