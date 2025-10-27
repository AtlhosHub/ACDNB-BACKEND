package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.filter.InteressadosFilter;

import java.util.List;

public interface ListaEsperaGateway {
    ListaEspera adicionarInteressado(ListaEspera listaEspera);
    List<ListaEspera> listarFiltro(InteressadosFilter interessadosFilter);
    List<ListaEspera> listarTodos();
    ListaEspera buscarPorId(int id);
    void deletarInteressado(int id);
    ListaEspera atualizarInteressado(ListaEspera listaEspera);
}
