package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;

import java.util.List;

public interface GerarMensalidade {
    public List<Mensalidade> execute(Aluno aluno);
}
