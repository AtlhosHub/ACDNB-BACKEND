package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;

public class RemoverUsuarioUseCaseImpl implements RemoverUsuarioUseCase {
    private final RemoverUsuarioUseCase removerUsuarioUseCase;
    public RemoverUsuarioUseCaseImpl() {
        this.removerUsuarioUseCase = new RemoverUsuarioUseCaseImpl();
    }

    @Override
    public Usuario execute(int id) {
        return null;
    }
}
