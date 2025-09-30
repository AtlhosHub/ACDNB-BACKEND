package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;

import java.util.Optional;

public class VerificarEmailCadastradoUseCaseImpl implements VerificarEmailCadastradoUseCase {
    public final AlunoGateway alunoGateway;

    public VerificarEmailCadastradoUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public Optional<Aluno> execute(String email) {
        return alunoGateway.buscarAlunoPorEmailOuResponsavelEmail(email);
    }
}
