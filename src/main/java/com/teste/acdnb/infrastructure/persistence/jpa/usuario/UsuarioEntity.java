package com.teste.acdnb.infrastructure.persistence.jpa.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.acdnb.core.domain.shared.valueobject.Nome;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do usuário", example = "1")
    private int id;

    @NotBlank(message = "O nome do usuário não pode ficar em branco")

    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;

    @Email(message = "O e-mail deve ser válido (Possuir @)")
    @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
    private String email;

    @Size(min = 8, message = "Sua senha deve possuir no mínimo 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$", message = "Sua senha deve possuir no mínimo 8 caracteres, 1 caractere MAIÚSCULO, 1 caractere minúsculo, 1 número e 1 caractere especial (!@#$%&*,.;)")
    @Schema(description = "Senha do usuário", example = "Senha@123")
    private String senha;

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

    @Schema(description = "Cargo do usuário", example = "Administrador")
    private String cargo;

    @CreationTimestamp
    @Schema(description = "Data de inclusão do usuário no sistema", example = "2025-04-21T10:15:30")
    private LocalDateTime dataInclusao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_inclusao_id")
    private UsuarioEntity usuarioInclusao;

//    @ManyToOne
//    @JoinColumn(name = "usuario_inclusao_id")
//    @JsonIgnoreProperties({"usuarioInclusao", "usuariosCadastrados", "alunos", "interessados"})
//    @Schema(description = "Usuário que incluiu este usuário no sistema")
//    private UsuarioEntity usuarioInclusao;
//
//    @OneToMany(mappedBy = "usuarioInclusao")
//    @JsonIgnoreProperties({"usuarioInclusao", "usuariosCadastrados", "alunos", "interessados"})
//    @Schema(description = "Lista de usuários cadastrados por este usuário")
//    private List<UsuarioEntity> usuariosCadastrados = new ArrayList<>();

//    @OneToMany(mappedBy = "usuarioInclusao")
//    @JsonIgnoreProperties("usuarioInclusao")
//    @Schema(description = "Lista de alunos associados a este usuário")
//    private List<Aluno> alunos = new ArrayList<>();
//
//    @OneToMany(mappedBy = "usuarioInclusao")
//    @JsonIgnoreProperties("usuarioInclusao")
//    @Schema(description = "Lista de interessados associados a este usuário")
//    private List<ListaEspera> interessados = new ArrayList<>();


}
