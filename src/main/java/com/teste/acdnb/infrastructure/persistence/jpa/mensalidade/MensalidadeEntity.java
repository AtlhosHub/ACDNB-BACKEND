package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.enums.FormaPagamento;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante.ComprovanteEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade.ValorMensalidadeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensalidade")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class MensalidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da mensalidade", example = "1")
    private int id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    @Schema(description = "Aluno associado à mensalidade")
    @JsonIgnore
    private Aluno aluno;

    @Schema(description = "Data de vencimento da mensalidade", example = "2025-05-01")
    private LocalDate dataVencimento;

    @Schema(description = "Data de pagamento da mensalidade", example = "2025-04-21T10:15:30")
    private LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Status da mensalidade", example = "PAGO")
    private StatusPagamento statusPagamento;

    @Schema(description = "Indica se a mensalidade foi alterada automaticamente", example = "true")
    private boolean alteracaoAutomatica = false;

    @ManyToOne
    @JoinColumn(name = "valor_mensalidade_id")
    @Schema(description = "Valor da mensalidade")
    private ValorMensalidadeEntity valor;

    @Column(name = "forma_pagamento", nullable = true)
    @Schema(description = "Forma de pagamento usada", example = "Pix")
    private FormaPagamento formaPagamento;

    @OneToOne
    @JoinColumn(name = "comprovante_id")
    @Schema(description = "Comprovante associado à mensalidade")
    private ComprovanteEntity comprovante;
}
