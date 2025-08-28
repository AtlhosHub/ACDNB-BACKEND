package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;

public interface RemoverUsuarioUseCase {
    public Usuario execute(int id);
}
