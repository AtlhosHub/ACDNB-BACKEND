package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.usuario.Usuario;

import java.util.List;

public class ListarUsuariosUseCaseImpl implements ListarUsuariosUseCase {
    private final UsuarioGateway usuarioGateway;
    public ListarUsuariosUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public List<Usuario> execute() {
        return usuarioGateway.listarUsuarios();
    }
}
