package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import java.util.List;

public interface ListaEsperaGateway {
    ListaEspera adicionarInteressado(ListaEspera listaEspera);
    List<ListaEspera> listarTodos();
    ListaEspera buscarPorId(int id);
}
