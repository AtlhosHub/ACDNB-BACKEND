package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;

import java.util.List;

public interface HorarioPreferenciaGateway {
    HorarioPreferencia adicionarHorarioPreferencia(HorarioPreferencia horarioPreferencia);
    HorarioPreferencia buscarPorId(Integer id);
    List<HorarioPreferencia> buscarTodos();
    HorarioPreferencia atualizar(HorarioPreferencia horarioPreferencia);
    void deletar(Integer id);
}
