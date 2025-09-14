package com.teste.acdnb.core.application.usecase.horarioPreferencia;

import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.infrastructure.dto.HorarioPreferenciaDTO;

public interface AdicionarHorarioPreferenciaUseCase {
    HorarioPreferencia execute(HorarioPreferenciaDTO horarioPreferenciaDTO);
}
