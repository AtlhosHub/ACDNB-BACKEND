package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;

public interface BuscarUsuarioPorIdUseCase {
    public Usuario execute(int id);
}
