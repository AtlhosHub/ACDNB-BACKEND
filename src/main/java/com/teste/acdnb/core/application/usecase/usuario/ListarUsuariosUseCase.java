package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;

import java.util.List;

public interface ListarUsuariosUseCase {
    public List<Usuario> execute();
}
