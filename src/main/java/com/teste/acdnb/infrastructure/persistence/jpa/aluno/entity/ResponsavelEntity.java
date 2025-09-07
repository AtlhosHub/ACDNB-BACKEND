package com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "responsavel")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Schema(description = "Entidade que representa um responsável no sistema")
public class ResponsavelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do responsável", example = "1")
    private int id;

    @Schema(description = "Nome completo do responsável", example = "Ana Pereira")
    private String nome;

    @Schema(description = "CPF do responsável", example = "98765432100")
    private String cpf;

    @Schema(description = "Número de celular do responsável", example = "(11) 91234-5678")
    private String celular;

    @Schema(description = "E-mail do responsável", example = "ana.pereira@email.com")
    private String email;

    @Schema(description = "RG do responsável", example = "987654321")
    private String rg;

    @Schema(description = "Número de telefone do responsável", example = "(11) 1234-5678")
    private String telefone;

    @Schema(description = "Nome social do responsável, caso aplicável", example = "Gustavo Pereira")
    private String nomeSocial;

    @Schema(description = "Gênero do responsável", example = "Feminino")
    private String genero;

    @Schema(description = "Prossissão do responsável", example = "Engenheiro")
    private String profissao;

    @ManyToMany(mappedBy = "responsaveis")
    @JsonIgnoreProperties("responsaveis")
    @Schema(description = "Lista de alunos associados ao responsável")
    private List<AlunoEntity> alunos = new ArrayList<>();
}
