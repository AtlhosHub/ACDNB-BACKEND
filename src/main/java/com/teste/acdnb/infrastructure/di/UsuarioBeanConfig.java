package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.application.usecase.usuario.AdicionarUsuarioUseCase;
import com.teste.acdnb.core.application.usecase.usuario.AdicionarUsuarioUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioBeanConfig {
    @Bean
    public AdicionarUsuarioUseCase adicionarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new AdicionarUsuarioUseCaseImpl(usuarioGateway);
    }
}
