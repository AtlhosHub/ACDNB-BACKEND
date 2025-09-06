package com.teste.acdnb.core.application.usecase.horarioPreferencia;

import com.teste.acdnb.core.application.gateway.HorarioPreferenciaGateway;

public class DeletarHorarioPreferenciaUseCaseImpl implements DeletarHorarioPreferenciaUseCase {

    private final HorarioPreferenciaGateway horarioPreferenciaGateway;

    public DeletarHorarioPreferenciaUseCaseImpl(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        this.horarioPreferenciaGateway = horarioPreferenciaGateway;
    }

    @Override
    public void execute(Integer id) {
        horarioPreferenciaGateway.deletar(id);
    }
}
