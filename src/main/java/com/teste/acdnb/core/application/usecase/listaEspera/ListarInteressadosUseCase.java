package com.teste.acdnb.core.application.usecase.listaEspera;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.filter.InteressadosFilter;

import java.util.List;

public interface ListarInteressadosUseCase {
    List<ListaEspera> execute(InteressadosFilter filter);
}
