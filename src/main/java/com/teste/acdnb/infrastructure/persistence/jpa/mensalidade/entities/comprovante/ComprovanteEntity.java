package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.valueobject.ValoresComprovante;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comprovante")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Schema(description = "Entidade que representa um comprovante de pagamento")
public class ComprovanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do comprovante", example = "1")
    private int id;

    @Schema(description = "Nome do remetente do pagamento", example = "Carlos Oliveira")
    private String nomeRemetente;

    @Schema(description = "Banco de origem do pagamento", example = "Banco do Brasil")
    private String bancoOrigem;

    @Embedded
    private ValoresComprovante valores;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Data e hora do envio do comprovante", example = "2025-04-21T10:15:30")
    private LocalDateTime dataEnvio;

    @Schema(description = "Usuário que irá receber o comprovante", example = "Walter White")
    private String contaDestino;

    @Schema(description = "Banco do Destinatario", example = "Caixa Econômica Federal")
    private String bancoDestino;
}
