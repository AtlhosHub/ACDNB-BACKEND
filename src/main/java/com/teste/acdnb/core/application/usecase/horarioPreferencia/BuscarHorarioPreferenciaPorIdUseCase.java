package com.teste.acdnb.core.application.usecase.horarioPreferencia;

import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;

public interface BuscarHorarioPreferenciaPorIdUseCase {
    HorarioPreferencia execute(Integer id);
}
