package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioDTO;

public interface AdicionarUsuarioUseCase {
    public Usuario execute(UsuarioDTO usuario);
}
