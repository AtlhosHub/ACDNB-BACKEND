package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioFiltroDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioResponseDTO;

import java.util.List;

public class BuscarUsuariosPorFiltroUseCaseImpl implements  BuscarUsuariosPorFiltroUseCase {
    private final UsuarioGateway usuarioGateway;

    public BuscarUsuariosPorFiltroUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }


    @Override
    public List<UsuarioResponseDTO> execute(UsuarioFiltroDTO usuarioFiltroDTO) {
        List<Usuario> usuarios = usuarioGateway.buscarUsuariosPorNome(usuarioFiltroDTO);

        return usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getNome().getValue(),
                        usuario.getEmail().getValue(),
                        usuario.getCelular().getValue(),
                        usuario.getDataNascimento().getValue(),
                        usuario.getNomeSocial() != null ? usuario.getNomeSocial().getValue() : null,
                        usuario.getGenero(),
                        usuario.getTelefone().getValue(),
                        usuario.getCargo()
                ))
                .toList();
    }

}
