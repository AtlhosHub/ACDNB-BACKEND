package com.teste.acdnb.core.application.usecase.listaEspera;

import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;

public class BuscarInteressadoUseCaseImpl implements BuscarInteressadoUseCase {

    private final ListaEsperaGateway listaEsperaGateway;

    public BuscarInteressadoUseCaseImpl(ListaEsperaGateway listaEsperaGateway) {
        this.listaEsperaGateway = listaEsperaGateway;
    }

    @Override
    public ListaEspera execute(int id) {
        return listaEsperaGateway.buscarPorId(id);
    }
}
