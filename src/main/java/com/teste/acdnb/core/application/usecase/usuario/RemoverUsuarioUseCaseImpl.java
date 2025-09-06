package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.exception.ResourceNotFoundException;
import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.usuario.Usuario;

import java.util.List;

public class RemoverUsuarioUseCaseImpl implements RemoverUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;
    public RemoverUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void execute(int id) {
        Usuario usuario = usuarioGateway.buscarUsuarioPorId(id) .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        List<Usuario> usuariosIncluidos = usuarioGateway.buscarUsuariosPorUsuarioInclusao(usuario);

        usuariosIncluidos.forEach(usuarioIncluido -> {
            usuarioIncluido.setUsuarioInclusao(null);
            usuarioGateway.atualizarUsuario(usuarioIncluido);
        });

        usuarioGateway.removerUsuarioPorId(id);
    }
}
