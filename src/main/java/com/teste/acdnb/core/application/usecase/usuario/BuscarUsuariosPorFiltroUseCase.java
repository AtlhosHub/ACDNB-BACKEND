package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.infrastructure.dto.usuario.UsuarioFiltroDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioRequestDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioResponseDTO;

import java.util.List;

public interface BuscarUsuariosPorFiltroUseCase {
    List<UsuarioResponseDTO> execute(UsuarioFiltroDTO usuarioFiltroDTO);
}
