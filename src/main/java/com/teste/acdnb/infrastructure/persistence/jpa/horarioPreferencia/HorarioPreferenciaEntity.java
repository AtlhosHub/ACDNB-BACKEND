package com.teste.acdnb.infrastructure.persistence.jpa.horarioPreferencia;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "horario_preferencia")
public class HorarioPreferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "horario_aula_inicio", nullable = false)
    private LocalDateTime horarioAulaInicio;

    @Column(name = "horario_aula_fim", nullable = false)
    private LocalDateTime horarioAulaFim;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDateTime dataInclusao;

    public HorarioPreferenciaEntity() {
    }

    public HorarioPreferenciaEntity(Integer id, LocalDateTime horarioAulaInicio, LocalDateTime horarioAulaFim, LocalDateTime dataInclusao) {
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

    public LocalDateTime getHorarioAulaInicio() {
        return horarioAulaInicio;
    }

    public void setHorarioAulaInicio(LocalDateTime horarioAulaInicio) {
        this.horarioAulaInicio = horarioAulaInicio;
    }

    public LocalDateTime getHorarioAulaFim() {
        return horarioAulaFim;
    }

    public void setHorarioAulaFim(LocalDateTime horarioAulaFim) {
        this.horarioAulaFim = horarioAulaFim;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}