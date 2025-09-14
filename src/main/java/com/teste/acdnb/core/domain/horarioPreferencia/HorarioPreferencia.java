package com.teste.acdnb.core.domain.horarioPreferencia;

import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;

public class HorarioPreferencia {
    private Integer id;
    DataInclusao  horarioAulaInicio;
    DataInclusao  horarioAulaFim;
    DataInclusao dataInclusao = DataInclusao.of(java.time.LocalDateTime.now());

    public HorarioPreferencia() {
    }

    public HorarioPreferencia(Integer id, DataInclusao horarioAulaInicio, DataInclusao horarioAulaFim, DataInclusao dataInclusao) {
        this.id = id;
        this.horarioAulaInicio = horarioAulaInicio;
        this.horarioAulaFim = horarioAulaFim;
        this.dataInclusao = dataInclusao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DataInclusao getHorarioAulaInicio() {
        return horarioAulaInicio;
    }

    public void setHorarioAulaInicio(DataInclusao horarioAulaInicio) {
        this.horarioAulaInicio = horarioAulaInicio;
    }

    public DataInclusao getHorarioAulaFim() {
        return horarioAulaFim;
    }

    public void setHorarioAulaFim(DataInclusao horarioAulaFim) {
        this.horarioAulaFim = horarioAulaFim;
    }

    public DataInclusao getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(DataInclusao dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
