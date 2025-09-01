package com.teste.acdnb.infrastructure.dto.aluno;

import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.core.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record AlunoDTO (
     String nome,
     String email,
     LocalDateTime dataNascimento,
     String cpf,
     String rg,
     String nomeSocial,
     String genero,
     String celular,
     String telefone,
     String nacionalidade,
     String naturalidade,
     String profissao,
     String deficiencia,
     boolean ativo,
     boolean atestado,
     boolean autorizado,
     LocalDateTime dataInclusao,
     Endereco endereco,
     List<Responsavel> responsavel,
     Usuario usuarioInclusao
){}
