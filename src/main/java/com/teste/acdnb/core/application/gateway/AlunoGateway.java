package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.infrastructure.filter.AlunoFilter;

import java.util.List;
import java.util.Optional;

public interface AlunoGateway {
    boolean existsByEmailIgnoreCaseOrCpfOrRg(String email, String cpf, String rg);
    boolean existsByCpfOrRg(String cpf, String rg);
    Aluno salvarAluno(Aluno aluno);

    Optional<Endereco> findEndereco(Endereco endereco);
    Endereco saveEndereco(Endereco endereco);

    Optional<Responsavel> findResponsavelPorCpf(String cpf);
    Responsavel saveResponsavel(Responsavel responsavel);

    List<Aluno> listarAlunos();

    List<Aluno> listarAlunosFiltro(AlunoFilter filter);

    boolean existsById(int id);

    Aluno buscarAlunoPorId(int id);

    void deletarAluno(int id);

    boolean existsByEmailIgnoreCaseAndIdIsNot(String email, int id);

    boolean existsByCpfAndIdIsNot(String cpf, int id);

    boolean existsByRgAndIdIsNot(String rg, int id);

    List<Aluno> listarAniversariantes();

    int qtdAlunosAtivos();
}
