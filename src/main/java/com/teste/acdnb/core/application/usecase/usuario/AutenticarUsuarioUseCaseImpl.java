package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.exception.AuthenticationException;
import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioLoginDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioMapper;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioRequestDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioTokenDTO;
import com.teste.acdnb.infrastructure.security.config.GerenciadorTokenJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class AutenticarUsuarioUseCaseImpl implements  AutenticarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final AuthenticationManager authenticationManager;
    private GerenciadorTokenJWT gerenciadorTokenJWT;

    public AutenticarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway, AuthenticationManager authenticationManager,  GerenciadorTokenJWT gerenciadorTokenJWT) {
        this.usuarioGateway = usuarioGateway;
        this.authenticationManager = authenticationManager;
        this.gerenciadorTokenJWT = gerenciadorTokenJWT;
    }

    @Override
    public UsuarioTokenDTO execute(UsuarioLoginDTO usuarioLogin) {
        Usuario usuarioAutenticado = usuarioGateway.buscarUsuarioPorEmail(usuarioLogin.getEmail())
                .orElseThrow(() -> new AuthenticationException("E-mail ou senha inválidos"));

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(usuarioLogin.getEmail(), usuarioLogin.getSenha());
        final Authentication authentication = authenticationManager.authenticate(credentials);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJWT.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }
}
