package com.teste.acdnb.infrastructure.persistence.jpa.horarioPreferencia;

import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;
import org.springframework.stereotype.Component;

@Component
public class HorarioPreferenciaEntityMapper {

    public HorarioPreferenciaEntity toEntity(HorarioPreferencia domain) {
        if (domain == null) return null;

        return new HorarioPreferenciaEntity(
                domain.getId(),
                domain.getHorarioAulaInicio() != null ? domain.getHorarioAulaInicio().getValue() : null,
                domain.getHorarioAulaFim() != null ? domain.getHorarioAulaFim().getValue() : null,
                domain.getDataInclusao() != null ? domain.getDataInclusao().getValue() : null
        );
    }

    public HorarioPreferencia toDomain(HorarioPreferenciaEntity entity) {
        if (entity == null) return null;

        return new HorarioPreferencia(
                entity.getId(),
                entity.getHorarioAulaInicio() != null ? DataInclusao.of(entity.getHorarioAulaInicio()) : null,
                entity.getHorarioAulaFim() != null ? DataInclusao.of(entity.getHorarioAulaFim()) : null,
                entity.getDataInclusao() != null ? DataInclusao.of(entity.getDataInclusao()) : null
        );
    }
}