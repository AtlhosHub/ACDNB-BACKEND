package com.teste.acdnb.core.application.usecase.horarioPreferencia;

import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.infrastructure.dto.HorarioPreferenciaDTO;

public interface AtualizarHorarioPreferenciaUseCase {
    HorarioPreferencia execute(Integer id, HorarioPreferenciaDTO dto);
}