package com.teste.acdnb.core.application.usecase.horarioPreferencia;

import com.teste.acdnb.core.application.gateway.HorarioPreferenciaGateway;
import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;
import com.teste.acdnb.infrastructure.dto.HorarioPreferenciaDTO;
import com.teste.acdnb.infrastructure.util.DateParser;

public class AdicionarHorarioPreferenciaUseCaseImpl implements AdicionarHorarioPreferenciaUseCase {

    private final HorarioPreferenciaGateway horarioPreferenciaGateway;

    public AdicionarHorarioPreferenciaUseCaseImpl(HorarioPreferenciaGateway horarioPreferenciaGateway) {
        this.horarioPreferenciaGateway = horarioPreferenciaGateway;
    }

    @Override
    public HorarioPreferencia execute(HorarioPreferenciaDTO dto) {
        var horarioPreferencia = new HorarioPreferencia();
        horarioPreferencia.setHorarioAulaInicio(DataInclusao.of(DateParser.parse(dto.horarioAulaInicio())));
        horarioPreferencia.setHorarioAulaFim(DataInclusao.of(DateParser.parse(dto.horarioAulaFim())));
        horarioPreferencia.setDataInclusao(DataInclusao.of(DateParser.parse(dto.dataInclusao())));

        return horarioPreferenciaGateway.adicionarHorarioPreferencia(horarioPreferencia);
    }
}