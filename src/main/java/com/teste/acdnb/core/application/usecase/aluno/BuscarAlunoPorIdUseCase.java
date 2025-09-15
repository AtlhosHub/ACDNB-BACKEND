package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.infrastructure.dto.aluno.AlunoInfoDTO;

public interface BuscarAlunoPorIdUseCase {
    AlunoInfoDTO execute(int id);
}
