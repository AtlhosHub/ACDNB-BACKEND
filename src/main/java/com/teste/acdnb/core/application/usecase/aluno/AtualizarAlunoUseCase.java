package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.domain.aluno.Aluno;

public interface AtualizarAlunoUseCase {
    Aluno execute(Aluno aluno, int id);
}
