package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.exception.ResourceNotFoundException;
import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.usuario.Usuario;

public class BuscarUsuarioPorIdUseCaseImpl implements BuscarUsuarioPorIdUseCase {
    private final UsuarioGateway usuarioGateway;
    public BuscarUsuarioPorIdUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Usuario execute(int id) {
        return usuarioGateway.buscarUsuarioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }
}
