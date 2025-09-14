package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.infrastructure.dto.usuario.UsuarioLoginDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioRequestDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioTokenDTO;

public interface AutenticarUsuarioUseCase {
    public UsuarioTokenDTO execute(UsuarioLoginDTO usuario);
}
