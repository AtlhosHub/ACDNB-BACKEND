package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.application.usecase.usuario.*;
import com.teste.acdnb.infrastructure.security.AutenticacaoService;
import com.teste.acdnb.infrastructure.security.config.GerenciadorTokenJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsuarioBeanConfig {
    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJWT gerenciadorTokenJWT;

    public UsuarioBeanConfig(AuthenticationManager authenticationManager, GerenciadorTokenJWT gerenciadorTokenJWT) {
        this.authenticationManager = authenticationManager;
        this.gerenciadorTokenJWT = gerenciadorTokenJWT;
    }

    @Bean
    public AdicionarUsuarioUseCase adicionarUsuarioUseCase(UsuarioGateway usuarioGateway, PasswordEncoder passwordEncoder) {
        return new AdicionarUsuarioUseCaseImpl(usuarioGateway, passwordEncoder);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(UsuarioGateway usuarioGateway) {
        return new ListarUsuariosUseCaseImpl(usuarioGateway);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorId(UsuarioGateway usuarioGateway) {
        return new BuscarUsuarioPorIdUseCaseImpl(usuarioGateway);
    }

    @Bean
    public RemoverUsuarioUseCase removerUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new RemoverUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean AtualizarUsuarioUseCase atualizarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new AtualizarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean BuscarUsuariosPorFiltroUseCase buscarUsuariosPorFiltroUseCase(UsuarioGateway usuarioGateway) {
        return new BuscarUsuariosPorFiltroUseCaseImpl(usuarioGateway);
    }

    @Bean AutenticarUsuarioUseCase autenticarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new AutenticarUsuarioUseCaseImpl(usuarioGateway, authenticationManager, gerenciadorTokenJWT );
    }
}
