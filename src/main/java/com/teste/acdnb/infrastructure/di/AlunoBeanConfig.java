package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.application.usecase.aluno.AdicionarAlunoUseCase;
import com.teste.acdnb.core.application.usecase.aluno.AdicionarAlunoUseCaseImpl;
import com.teste.acdnb.core.application.usecase.aluno.ListarAlunosUseCase;
import com.teste.acdnb.core.application.usecase.aluno.ListarAlunosUseCaseImpl;
import com.teste.acdnb.core.domain.mensalidade.factory.MensalidadeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlunoBeanConfig {
    @Bean
    public AdicionarAlunoUseCase adicionarAlunoUseCase(AlunoGateway alunoGateway, ValorMensalidadeGateway valorMensalidadeGateway, MensalidadeFactory mensalidadeFactory, MensalidadeGateway mensalidadeGateway) {
        return new AdicionarAlunoUseCaseImpl(alunoGateway, valorMensalidadeGateway, mensalidadeFactory, mensalidadeGateway);
    }

    @Bean
    public ListarAlunosUseCase listarAlunosUseCase(AlunoGateway alunoGateway) {
        return new ListarAlunosUseCaseImpl(alunoGateway);
    }

    @Bean
    public MensalidadeFactory mensalidadeFactory() {
        return new MensalidadeFactory();
    }
}
