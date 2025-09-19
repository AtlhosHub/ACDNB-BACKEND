package com.teste.acdnb.core.application.usecase.listaEspera;

import com.teste.acdnb.core.domain.listaEspera.ListaEspera;

public interface BuscarInteressadoUseCase {
    ListaEspera execute(int id);
}