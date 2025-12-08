package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.domain.usuario.valueobject.Senha;

public interface RecuperarSenhaUseCase {
    void solicitarRecuperacaoSenha(String email);
    boolean validarToken(String token);
    void resetarSenha(String token, String novaSenha);
}
