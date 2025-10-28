package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.valorMensalidade;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "valor_mensalidade")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Schema(description = "Entidade que o valor das mensalidades no sistema")
public class ValorMensalidadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do valor de mensalidade", example = "1")
    private int id;

    @NotNull(message = "Valor não pode ser nulo")
    @DecimalMin(value = "0.0", message = "Valor deve ser maior que zero")
    @Schema(description = "Valor da mensalidade", example = "150.00")
    private BigDecimal valor;

    @Schema(description = "Valor inserido para pagamentos manuais", example = "false")
    private boolean manualFlag = false;

    @Schema(description = "Valor inserido para pagamentos com desconto", example = "false")
    private boolean desconto = false;

    @CreationTimestamp
    @Schema(description = "Data de inclusão do valor da mensalidade no sistema", example = "2025-04-21T10:15:30")
    private LocalDateTime dataInclusao;
}
