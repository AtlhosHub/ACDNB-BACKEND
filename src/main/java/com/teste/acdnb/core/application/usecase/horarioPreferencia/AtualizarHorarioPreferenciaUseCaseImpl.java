package com.teste.acdnb.core.application.usecase.horarioPreferencia;

import com.teste.acdnb.core.application.gateway.HorarioPreferenciaGateway;
import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;
import com.teste.acdnb.infrastructure.dto.HorarioPreferenciaDTO;
import com.teste.acdnb.infrastructure.util.DateParser;
import com.teste.acdnb.infrastructure.util.TimeParser;

public class AtualizarHorarioPreferenciaUseCaseImpl implements AtualizarHorarioPreferenciaUseCase {

    private final HorarioPreferenciaGateway horarioPreferenciaGateway;

    public AtualizarHorarioPreferenciaUseCaseImpl(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        this.horarioPreferenciaGateway = horarioPreferenciaGateway;
    }

    @Override
    public HorarioPreferencia execute(Integer id, HorarioPreferenciaDTO dto) {
        HorarioPreferencia existente = horarioPreferenciaGateway.buscarPorId(id);

        if (dto.horarioAulaInicio() != null)
            existente.setHorarioAulaInicio(TimeParser.parse(dto.horarioAulaInicio()));

        if (dto.horarioAulaFim() != null)
            existente.setHorarioAulaFim(TimeParser.parse(dto.horarioAulaFim()));

        if (dto.dataInclusao() != null)
            existente.setDataInclusao(DataInclusao.of(DateParser.parse(dto.dataInclusao())));

        return horarioPreferenciaGateway.atualizar(existente);
    }
}