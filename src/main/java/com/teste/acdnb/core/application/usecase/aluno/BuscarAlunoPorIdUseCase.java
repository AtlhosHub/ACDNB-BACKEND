package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.domain.aluno.Aluno;

public interface BuscarAlunoPorIdUseCase {
    Aluno execute(int id);
}
