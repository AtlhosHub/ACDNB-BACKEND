package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;

import java.util.List;

public class ListarAlunosUseCaseImpl implements ListarAlunosUseCase{
    public final AlunoGateway alunoGateway;

    public ListarAlunosUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public List<Aluno> execute() {
        return alunoGateway.listarAlunos();
    }
}
