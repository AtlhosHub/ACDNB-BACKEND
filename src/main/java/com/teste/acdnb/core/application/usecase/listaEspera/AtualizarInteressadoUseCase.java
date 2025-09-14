package com.teste.acdnb.core.application.usecase.listaEspera;

import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.dto.ListaEsperaDTO;

public interface AtualizarInteressadoUseCase {
    ListaEspera execute(int id, ListaEsperaDTO dto);
}

