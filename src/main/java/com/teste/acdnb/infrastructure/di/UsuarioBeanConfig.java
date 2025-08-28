package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.application.usecase.usuario.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioBeanConfig {
    @Bean
    public AdicionarUsuarioUseCase adicionarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new AdicionarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(UsuarioGateway usuarioGateway) {
        return new ListarUsuariosUseCaseImpl(usuarioGateway);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorId(UsuarioGateway usuarioGateway) {
        return new BuscarUsuarioPorIdUseCaseImpl(usuarioGateway);
    }
}
