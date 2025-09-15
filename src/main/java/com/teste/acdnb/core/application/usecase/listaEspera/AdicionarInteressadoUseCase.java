package com.teste.acdnb.core.application.usecase.listaEspera;

import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.dto.ListaEsperaDTO;

public interface AdicionarInteressadoUseCase {
    public ListaEspera execute(ListaEsperaDTO listaEsperaDTO);
}
