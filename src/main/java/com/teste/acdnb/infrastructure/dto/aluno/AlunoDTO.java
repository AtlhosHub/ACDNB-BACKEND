package com.teste.acdnb.infrastructure.dto.aluno;

import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.core.domain.usuario.Usuario;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

public record AlunoDTO (

        @NotBlank(message = "Nome de usuário não pode ser vazio")
        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "Nome deve conter apenas letras e espaços")
        String nome,

        @Email(message = "Email deve ser válido (contém @ e domínio)")
        String email,

        @NotNull(message = "Data de nascimento não pode ser nula")
        @Past(message = "Data de nascimento deve ser anterior a data atual")
        LocalDate dataNascimento,

        @NotBlank(message = "CPF não pode ser vazio")
        @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$", message = "CPF deve ser válido")
        String cpf,

        @NotBlank(message = "RG não pode ser vazio")
        String rg,

        @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = "Nome social deve conter apenas letras e espaços")
        String nomeSocial,

        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$", message = "Celular deve ser um número válido (com DDD)")
        String celular,

        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$", message = "Telefone deve ser um número válido (com DDD)")
        String telefone,

        String genero,
        String nacionalidade,
        String naturalidade,
        String profissao,
        String deficiencia,
        boolean ativo,
        boolean atestado,
        boolean autorizado,
        LocalDateTime dataInclusao,

        @NotNull(message = "Endereço não pode ser nulo")
        Endereco endereco,

        List<Responsavel> responsaveis
//        Usuario usuarioInclusao
){}
