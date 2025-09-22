package com.teste.acdnb.infrastructure.dto.aluno;

import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record AlunoInfoDTO (
    int id,
    String nome,
    String email,
    LocalDate dataNascimento,
    String cpf,
    String rg,
    String nomeSocial,
    String genero,
    String celular,
    String nacionalidade,
    String naturalidade,
    String telefone,
    String profissao,
    boolean ativo,
    boolean temAtestado,
    String deficiencia,
    boolean autorizado,
    LocalDateTime dataInclusao,
    Endereco endereco,
    List<Responsavel> responsaveis
//    int usuarioInclusao
){}
