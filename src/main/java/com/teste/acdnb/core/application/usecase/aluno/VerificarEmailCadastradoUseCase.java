package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.domain.aluno.Aluno;

import java.util.Optional;

public interface VerificarEmailCadastradoUseCase {
    Optional<Aluno> execute(String email);
}
