package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.infrastructure.dto.aluno.AlunoAniversarioDTO;

import java.util.List;

public interface ListarAniversariosUseCase {
    List<AlunoAniversarioDTO> execute();
}
