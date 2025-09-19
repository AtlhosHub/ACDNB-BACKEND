package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.HorarioPreferenciaGateway;
import com.teste.acdnb.core.application.usecase.horarioPreferencia.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HorarioPreferenciaBeanConfig {

    @Bean
    public AdicionarHorarioPreferenciaUseCase adicionarHorarioPreferenciaUseCase(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        return new AdicionarHorarioPreferenciaUseCaseImpl(horarioPreferenciaGateway);
    }

    @Bean
    public ListarHorarioPreferenciaUseCase listarHorarioPreferenciaUseCase(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        return new ListarHorarioPreferenciaUseCaseImpl(horarioPreferenciaGateway);
    }

    @Bean
    public BuscarHorarioPreferenciaPorIdUseCase buscarHorarioPreferenciaPorIdUseCase(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        return new BuscarHorarioPreferenciaPorIdUseCaseImpl(horarioPreferenciaGateway);
    }

    @Bean
    public AtualizarHorarioPreferenciaUseCase atualizarHorarioPreferenciaUseCase(HorarioPreferenciaGateway gateway) {
        return new AtualizarHorarioPreferenciaUseCaseImpl(gateway);
    }

    @Bean
    public DeletarHorarioPreferenciaUseCase deletarHorarioPreferenciaUseCase(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        return new DeletarHorarioPreferenciaUseCaseImpl(horarioPreferenciaGateway);
    }

}

