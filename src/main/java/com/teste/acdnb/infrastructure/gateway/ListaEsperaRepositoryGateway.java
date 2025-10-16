package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.filter.InteressadosFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.specification.AlunoSpecification;
import com.teste.acdnb.infrastructure.persistence.jpa.listaEspera.ListaEsperaEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.listaEspera.ListaEsperaEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.listaEspera.ListaEsperaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListaEsperaRepositoryGateway implements ListaEsperaGateway {
    private final ListaEsperaRepository listaEsperaRepository;
    private final ListaEsperaEntityMapper listaEsperaEntityMapper;

    public ListaEsperaRepositoryGateway(ListaEsperaRepository listaEsperaRepository,
                                        ListaEsperaEntityMapper listaEsperaEntityMapper) {
        this.listaEsperaRepository = listaEsperaRepository;
        this.listaEsperaEntityMapper = listaEsperaEntityMapper;
    }

    @Override
    public ListaEspera adicionarInteressado(ListaEspera listaEspera) {
        ListaEsperaEntity entity = listaEsperaEntityMapper.toEntity(listaEspera);
        ListaEsperaEntity novoEntity = listaEsperaRepository.save(entity);
        return listaEsperaEntityMapper.toDomain(novoEntity);
    }

    @Override
    public List<ListaEspera> listarTodos(InteressadosFilter interessadosFitler) {
        Pageable pageable = PageRequest.of(
                interessadosFitler.offset() / interessadosFitler.limit(),
                interessadosFitler.limit(),
                Sort.by(Sort.Order.desc("dataInclusao"))
        );

        return listaEsperaRepository.findAll(pageable)
                .stream()
                .map(listaEsperaEntityMapper::toDomain)
                .toList();
    }

    @Override
    public ListaEspera buscarPorId(int id) {
        var entity = listaEsperaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interessado não encontrado com ID: " + id));
        return listaEsperaEntityMapper.toDomain(entity);
    }

    @Override
    public void deletarInteressado(int id) {
        if (!listaEsperaRepository.existsById(id)) {
            throw new RuntimeException("Interessado não encontrado com id: " + id);
        }
        listaEsperaRepository.deleteById(id);
    }

    @Override
    public ListaEspera atualizarInteressado(ListaEspera listaEspera) {
        ListaEsperaEntity entity = listaEsperaRepository.findById(listaEspera.getId())
                .orElseThrow(() -> new RuntimeException("Interessado não encontrado"));

        listaEsperaEntityMapper.updateEntityFromDomain(listaEspera, entity);

        ListaEsperaEntity updated = listaEsperaRepository.save(entity);
        return listaEsperaEntityMapper.toDomain(updated);
    }

}