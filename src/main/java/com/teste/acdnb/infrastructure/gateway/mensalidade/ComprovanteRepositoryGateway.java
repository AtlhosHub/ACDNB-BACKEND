package com.teste.acdnb.infrastructure.gateway.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.ComprovanteGateway;
import com.teste.acdnb.core.domain.mensalidade.entities.Comprovante;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.ComprovanteRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante.ComprovanteEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante.ComprovanteEntityMapper;

public class ComprovanteRepositoryGateway implements ComprovanteGateway {

    private final ComprovanteRepository comprovanteRepository;
    private final ComprovanteEntityMapper comprovanteEntityMapper;

    public ComprovanteRepositoryGateway(ComprovanteRepository comprovanteRepository,
                                        ComprovanteEntityMapper comprovanteEntityMapper) {
        this.comprovanteRepository = comprovanteRepository;
        this.comprovanteEntityMapper = comprovanteEntityMapper;
    }

    @Override
    public Comprovante salvar(Comprovante comprovante) {
        ComprovanteEntity entity = comprovanteEntityMapper.toEntity(comprovante);
        ComprovanteEntity saved = comprovanteRepository.save(entity);
        return comprovanteEntityMapper.toDomain(saved);
    }
}