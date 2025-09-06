package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.exception.ResourceNotFoundException;
import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.usuario.Usuario;

public class RemoverUsuarioUseCaseImpl implements RemoverUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;
    public RemoverUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void execute(int id) {
         usuarioGateway.buscarUsuarioPorId(id) .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuarioGateway.removerUsuarioPorId(id);
    }
}
