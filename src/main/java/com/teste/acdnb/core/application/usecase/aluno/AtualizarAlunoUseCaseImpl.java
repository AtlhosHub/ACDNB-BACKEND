package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;

public class AtualizarAlunoUseCaseImpl implements AtualizarAlunoUseCase{
    private final AlunoGateway alunoGateway;

    public AtualizarAlunoUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public Aluno execute(AlunoDTO aluno, int id) {
        if(!alunoGateway.existsById(id)){
            throw new RuntimeException("Aluno não encontrado");
        }
        return alunoGateway.atualizarAluno(new Aluno(), id);
    }
}
