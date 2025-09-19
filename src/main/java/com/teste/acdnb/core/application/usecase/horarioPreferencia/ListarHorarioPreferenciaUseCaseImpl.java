package com.teste.acdnb.core.application.usecase.horarioPreferencia;

import com.teste.acdnb.core.application.gateway.HorarioPreferenciaGateway;
import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import java.util.List;

public class ListarHorarioPreferenciaUseCaseImpl implements ListarHorarioPreferenciaUseCase {

    private final HorarioPreferenciaGateway horarioPreferenciaGateway;

    public ListarHorarioPreferenciaUseCaseImpl(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        this.horarioPreferenciaGateway = horarioPreferenciaGateway;
    }

    @Override
    public List<HorarioPreferencia> execute() {
        return horarioPreferenciaGateway.buscarTodos();
    }
}
