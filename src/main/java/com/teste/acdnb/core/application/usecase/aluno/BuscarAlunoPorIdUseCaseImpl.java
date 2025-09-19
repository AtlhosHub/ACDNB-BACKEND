package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;

public class BuscarAlunoPorIdUseCaseImpl implements BuscarAlunoPorIdUseCase{
    private final AlunoGateway alunoGateway;

    public BuscarAlunoPorIdUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public Aluno execute(int id) {
        if(!alunoGateway.existsById(id)){
            throw new RuntimeException("Aluno não encontrado");
        }
        return alunoGateway.buscarAlunoPorId(id);
    }
}
