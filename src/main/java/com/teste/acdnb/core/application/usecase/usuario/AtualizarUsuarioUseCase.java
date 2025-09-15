package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioRequestDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioResponseDTO;

public interface AtualizarUsuarioUseCase {
    public UsuarioResponseDTO execute(int id, UsuarioRequestDTO usuarioEditado);
}
