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
}
