package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;

import java.util.List;

public interface ListarAlunosMensalidades {
    List<AlunoComprovanteDTO> execute(ListarAlunosMensalidadeFilter filter);
}
