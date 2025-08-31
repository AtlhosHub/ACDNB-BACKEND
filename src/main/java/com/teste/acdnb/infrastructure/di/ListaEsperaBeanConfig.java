package com.teste.acdnb.infrastructure.di;

import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.core.application.usecase.listaEspera.AdicionarInteressadoUseCase;
import com.teste.acdnb.core.application.usecase.listaEspera.AdicionarInteressadoUseCaseImpl;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaEsperaBeanConfig {
    @Bean
    public AdicionarInteressadoUseCase adicionarInteressadoUseCase(
            ListaEsperaGateway listaEsperaGateway,
            UsuarioRepository usuarioRepository,
            UsuarioEntityMapper usuarioEntityMapper
    ) {
        return new AdicionarInteressadoUseCaseImpl(
                listaEsperaGateway,
                usuarioRepository,
                usuarioEntityMapper
        );
    }
}
