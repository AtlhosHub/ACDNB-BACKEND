package com.teste.acdnb.core.domain.listaEspera;

import java.time.LocalTime;

public class HorarioPreferencia {
    private int id;
    private LocalTime horarioAulaInicio;
    private LocalTime horarioAulaFim;
    private String diaSemana;

    public HorarioPreferencia() {
    }

    public HorarioPreferencia(int id, LocalTime horarioAulaInicio, LocalTime horarioAulaFim, String diaSemana) {
        this.id = id;
        this.horarioAulaInicio = horarioAulaInicio;
        this.horarioAulaFim = horarioAulaFim;
        this.diaSemana = diaSemana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
}
