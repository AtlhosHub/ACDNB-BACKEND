package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;

public interface AtualizarAlunoUseCase {
    Aluno execute(AlunoDTO aluno, int id);
}
