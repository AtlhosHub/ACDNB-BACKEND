package com.teste.acdnb.core.application.usecase.horarioPreferencia;

import com.teste.acdnb.core.application.gateway.HorarioPreferenciaGateway;
import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;

public class BuscarHorarioPreferenciaPorIdUseCaseImpl implements BuscarHorarioPreferenciaPorIdUseCase {

    private final HorarioPreferenciaGateway horarioPreferenciaGateway;

    public BuscarHorarioPreferenciaPorIdUseCaseImpl(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        this.horarioPreferenciaGateway = horarioPreferenciaGateway;
    }

    @Override
    public HorarioPreferencia execute(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do horário não pode ser nulo");
        }
        return horarioPreferenciaGateway.buscarPorId(id);
    }
}
