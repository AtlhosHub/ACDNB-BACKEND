package com.teste.acdnb.infrastructure.cache;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.specification.MensalidadeSpecification;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;

import java.util.List;

@Component
@Primary
public abstract class MensalidadeGatewayCached implements MensalidadeGateway {
    private final MensalidadeRepository mensalidadeRepository;

    protected MensalidadeGatewayCached(MensalidadeRepository mensalidadeRepository) {
        this.mensalidadeRepository = mensalidadeRepository;
    }

    @Override
    @Cacheable("listarAlunosFiltro")
    public List<Mensalidade> listarMensalidadesFiltro(ListarAlunosMensalidadeFilter filter){
        Specification<MensalidadeEntity> spec = MensalidadeSpecification.filtrarPor(filter);
        return MensalidadeEntityMapper.toDomainList(mensalidadeRepository.findAll(spec, Sort.by(Sort.Order.asc("dataVencimento"))));
    }
}