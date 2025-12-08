package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.core.domain.usuario.valueobject.Senha;
import com.teste.acdnb.infrastructure.dto.usuario.EmailRecuperacaoSenhaDTO;
import com.teste.acdnb.infrastructure.security.ProdutorMensagem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecuperarSenhaUseCaseImpl implements RecuperarSenhaUseCase {

    private final UsuarioGateway usuarioGateway;
    private final ProdutorMensagem produtorMensagem;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.frontend.url:http://localhost:5173}")
    private String frontendUrl;

    private static final int TOKEN_EXPIRACAO_HORAS = 24;

    public RecuperarSenhaUseCaseImpl(
            UsuarioGateway usuarioGateway,
            ProdutorMensagem produtorMensagem,
            PasswordEncoder passwordEncoder) {
        this.usuarioGateway = usuarioGateway;
        this.produtorMensagem = produtorMensagem;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void solicitarRecuperacaoSenha(String email) {
        Usuario usuario = usuarioGateway.buscarUsuarioPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));

        String token = UUID.randomUUID().toString();
        LocalDateTime expiracao = LocalDateTime.now().plusHours(TOKEN_EXPIRACAO_HORAS);

        usuarioGateway.atualizarTokenRecuperacao(email, token, expiracao);

        usuario.setTokenRecuperacaoSenha(token);
        usuario.setTokenExpiracao(expiracao);

        String linkRecuperacao = frontendUrl + "/recuperarSenha?token=" + token;

        String emailStr = usuario.getEmail() != null ? usuario.getEmail().getValue() : email;
        String nomeStr = usuario.getNome() != null ? usuario.getNome().getValue() : "Usuário";

        EmailRecuperacaoSenhaDTO emailDTO = new EmailRecuperacaoSenhaDTO(
                emailStr,
                nomeStr,
                token,
                linkRecuperacao
        );

        produtorMensagem.enviarEmailRecuperacaoSenha(emailDTO);
    }

    @Override
    public boolean validarToken(String token) {
        return usuarioGateway.buscarPorTokenRecuperacao(token)
                .map(usuario -> {
                    if (usuario.getTokenExpiracao() == null ||
                            usuario.getTokenExpiracao().isBefore(LocalDateTime.now())) {
                        return false;
                    }
                    return true;
                })
                .orElse(false);
    }

    @Override
    public void resetarSenha(String token, String novaSenha) {
        Usuario usuario = usuarioGateway.buscarPorTokenRecuperacao(token)
                .orElseThrow(() -> new RuntimeException("Token inválido ou expirado"));

        if (usuario.getTokenExpiracao() == null ||
                usuario.getTokenExpiracao().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        usuario.setSenha(Senha.of(passwordEncoder.encode((CharSequence) novaSenha)));

        usuario.setTokenRecuperacaoSenha(null);
        usuario.setTokenExpiracao(null);

        usuarioGateway.atualizarUsuario(usuario);
    }
}

