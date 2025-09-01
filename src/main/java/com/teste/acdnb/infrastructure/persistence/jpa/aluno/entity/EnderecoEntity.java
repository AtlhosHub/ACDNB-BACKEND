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
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um endereço no sistema")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do endereço", example = "1")
    private int id;

    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String logradouro;

    @Schema(description = "Número do logradouro", example = "123")
    private String numLog;

    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "Estado do endereço", example = "São Paulo")
    private String estado;

    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;

//    @OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("endereco")
//    @Schema(description = "Lista de alunos associados ao endereço")
//    private List<AlunoEntity> alunos = new ArrayList<>();
}