package com.teste.acdnb.core.domain.horarioPreferencia;

import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;

import java.time.LocalTime;

public class HorarioPreferencia {
    private Integer id;
    LocalTime horarioAulaInicio;
    LocalTime  horarioAulaFim;
    DataInclusao dataInclusao = DataInclusao.of(java.time.LocalDateTime.now());

    public HorarioPreferencia() {
    }

    public HorarioPreferencia(Integer id, LocalTime horarioAulaInicio, LocalTime horarioAulaFim, DataInclusao dataInclusao) {
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

    public LocalTime getHorarioAulaInicio() {
        return horarioAulaInicio;
    }

    public void setHorarioAulaInicio(LocalTime horarioAulaInicio) {
        this.horarioAulaInicio = horarioAulaInicio;
    }

    public LocalTime getHorarioAulaFim() {
        return horarioAulaFim;
    }

    public void setHorarioAulaFim(LocalTime horarioAulaFim) {
        this.horarioAulaFim = horarioAulaFim;
    }

    public DataInclusao getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(DataInclusao dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
