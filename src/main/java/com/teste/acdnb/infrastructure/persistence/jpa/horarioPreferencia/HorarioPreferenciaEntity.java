package com.teste.acdnb.infrastructure.persistence.jpa.horarioPreferencia;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "horario_preferencia")
public class HorarioPreferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "horario_aula_inicio", nullable = false)
    private LocalTime horarioAulaInicio;

    @Column(name = "horario_aula_fim", nullable = false)
    private LocalTime horarioAulaFim;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

    public HorarioPreferenciaEntity() {
    }

    public HorarioPreferenciaEntity(Integer id, LocalTime horarioAulaInicio, LocalTime horarioAulaFim, LocalDateTime dataInclusao) {
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

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}