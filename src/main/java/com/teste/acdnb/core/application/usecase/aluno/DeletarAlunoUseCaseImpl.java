package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;

public class DeletarAlunoUseCaseImpl implements DeletarAlunoUseCase{
    private final AlunoGateway alunoGateway;

    public DeletarAlunoUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public void execute(int id) {
        if(!alunoGateway.existsById(id)){
            throw new RuntimeException("Aluno não encontrado");
        }
        alunoGateway.deletarAluno(id);
    }
}
