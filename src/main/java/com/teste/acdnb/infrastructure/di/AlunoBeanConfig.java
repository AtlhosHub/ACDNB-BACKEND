package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ValorMensalidadeGateway;
import com.teste.acdnb.core.application.usecase.aluno.*;
import com.teste.acdnb.core.domain.mensalidade.factory.MensalidadeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlunoBeanConfig {
    @Bean
    public MensalidadeFactory mensalidadeFactory() {
        return new MensalidadeFactory();
    }

    @Bean
    public AdicionarAlunoUseCase adicionarAlunoUseCase(AlunoGateway alunoGateway, ValorMensalidadeGateway valorMensalidadeGateway, MensalidadeFactory mensalidadeFactory, MensalidadeGateway mensalidadeGateway) {
        return new AdicionarAlunoUseCaseImpl(alunoGateway, valorMensalidadeGateway, mensalidadeFactory, mensalidadeGateway);
    }

    @Bean
    public ListarAlunosUseCase listarAlunosUseCase(AlunoGateway alunoGateway) {
        return new ListarAlunosUseCaseImpl(alunoGateway);
    }

    @Bean
    public DeletarAlunoUseCase deletarAlunoUseCase(AlunoGateway alunoGateway) {
        return new DeletarAlunoUseCaseImpl(alunoGateway);
    }

    @Bean
    public BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase(AlunoGateway alunoGateway) {
        return new BuscarAlunoPorIdUseCaseImpl(alunoGateway);
    }

    @Bean
    public AtualizarAlunoUseCase atualizarAlunoUseCase(AlunoGateway alunoGateway) {
        return new AtualizarAlunoUseCaseImpl(alunoGateway);
    }

    @Bean
    public ListarAniversariosUseCase listarAniversariosUseCase(AlunoGateway alunoGateway) {
        return new ListarAniversariosUseCaseImpl(alunoGateway);
    }

    @Bean
    public QtdAlunosAtivosUseCase qtdAlunosAtivosUseCase(AlunoGateway alunoGateway) {
        return new QtdAlunosAtivosUseCaseImpl(alunoGateway);
    }
}
