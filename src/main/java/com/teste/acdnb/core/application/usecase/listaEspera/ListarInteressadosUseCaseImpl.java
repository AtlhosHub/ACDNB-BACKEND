package com.teste.acdnb.core.application.usecase.listaEspera;

import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.infrastructure.filter.InteressadosFilter;

import java.util.List;

public class ListarInteressadosUseCaseImpl implements ListarInteressadosUseCase {

    private final ListaEsperaGateway listaEsperaGateway;

    public ListarInteressadosUseCaseImpl(ListaEsperaGateway listaEsperaGateway) {
        this.listaEsperaGateway = listaEsperaGateway;
    }

    @Override
    public List<ListaEspera> execute(InteressadosFilter interessadosFilter) {
        return listaEsperaGateway.listarFiltro(interessadosFilter);
    }
}