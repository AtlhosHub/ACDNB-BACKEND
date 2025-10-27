package com.teste.acdnb.infrastructure.persistence.jpa.listaEspera;

import com.teste.acdnb.infrastructure.persistence.jpa.horarioPreferencia.HorarioPreferenciaEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lista_espera")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListaEsperaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do usuário", example = "1")
    private int id;

    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;

    @Email(message = "O e-mail deve ser válido (Possuir @)")
    @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
    private String email;

    @Schema(description = "Número de celular do usuário", example = "(11) 91234-5678")
    private String celular;

    @NotNull(message = "A data de nascimento deve ser preenchida")
    @Past(message = "A data deve ser uma data passada")
    @Schema(description = "Data de nascimento do usuário", example = "1990-05-15")
    private LocalDate dataNascimento;

    @Schema(description = "Nome social do usuário, caso aplicável", example = "Joana Silva")
    private String nomeSocial;

    @Schema(description = "Gênero do usuário", example = "Masculino")
    private String genero;

    @Schema(description = "Número de telefone do usuário", example = "(11) 1234-5678")
    private String telefone;

    @CreationTimestamp
    @Schema(description = "Data de inclusão do usuário no sistema", example = "2025-04-21T10:15:30")
    private LocalDateTime dataInclusao;

    @Schema(description = "Data de interesse do usuário", example = "2025-04-21T14:30:00")
    private LocalDateTime dataInteresse;

    @ManyToOne
    @JoinColumn(name = "horario_preferencia_id")
    private HorarioPreferenciaEntity horarioPreferencia;

    @ManyToOne
    @JoinColumn(name = "usuario_inclusao_id")
    private UsuarioEntity usuarioInclusao;

}
