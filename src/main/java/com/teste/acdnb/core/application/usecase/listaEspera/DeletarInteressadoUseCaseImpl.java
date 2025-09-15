package com.teste.acdnb.core.application.usecase.listaEspera;

import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;

public class DeletarInteressadoUseCaseImpl implements DeletarInteressadoUseCase {

    private final ListaEsperaGateway listaEsperaGateway;

    public DeletarInteressadoUseCaseImpl(ListaEsperaGateway listaEsperaGateway) {
        this.listaEsperaGateway = listaEsperaGateway;
    }

    @Override
    public void execute(int id) {
        listaEsperaGateway.deletarInteressado(id);
    }
}
