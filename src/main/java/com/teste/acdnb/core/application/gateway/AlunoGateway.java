package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;

import java.util.List;
import java.util.Optional;

public interface AlunoGateway {
    boolean existsByEmailIgnoreCaseOrCpfOrRg(String email, String cpf, String rg);
    boolean existsByCpfOrRg(String cpf, String rg);
    Aluno adicionarAluno(Aluno aluno);

    Optional<Endereco> findEndereco(Endereco endereco);
    Endereco saveEndereco(Endereco endereco);

    Optional<Responsavel> findResponsavelPorCpf(String cpf);
    Responsavel saveResponsavel(Responsavel responsavel);

    List<Aluno> listarAlunos();

    boolean existsById(int id);

    Aluno buscarAlunoPorId(int id);

    void deletarAluno(int id);

    Aluno atualizarAluno(Aluno aluno, int id);
}
