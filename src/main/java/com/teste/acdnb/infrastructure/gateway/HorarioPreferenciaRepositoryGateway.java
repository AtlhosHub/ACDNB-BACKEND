package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.HorarioPreferenciaGateway;
import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.infrastructure.persistence.jpa.horarioPreferencia.HorarioPreferenciaEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.horarioPreferencia.HorarioPreferenciaEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.horarioPreferencia.HorarioPreferenciaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HorarioPreferenciaRepositoryGateway implements HorarioPreferenciaGateway {

    private final HorarioPreferenciaRepository repository;
    private final HorarioPreferenciaEntityMapper mapper;
    private final HorarioPreferenciaRepository horarioPreferenciaRepository;
    private final HorarioPreferenciaEntityMapper horarioPreferenciaEntityMapper;

    public HorarioPreferenciaRepositoryGateway(HorarioPreferenciaRepository repository, HorarioPreferenciaEntityMapper mapper, HorarioPreferenciaRepository horarioPreferenciaRepository, HorarioPreferenciaEntityMapper horarioPreferenciaEntityMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.horarioPreferenciaRepository = horarioPreferenciaRepository;
        this.horarioPreferenciaEntityMapper = horarioPreferenciaEntityMapper;
    }

    @Override
    public HorarioPreferencia adicionarHorarioPreferencia(HorarioPreferencia horarioPreferencia) {
        HorarioPreferenciaEntity entity = mapper.toEntity(horarioPreferencia);
        HorarioPreferenciaEntity novoEntity = repository.save(entity);
        return mapper.toDomain(novoEntity);
    }

    @Override
    public HorarioPreferencia buscarPorId(Integer id) {
        return mapper.toDomain(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horário não encontrado")));
    }

    @Override
    public List<HorarioPreferencia> buscarTodos() {
        return horarioPreferenciaRepository.findAll()
                .stream()
                .map(horarioPreferenciaEntityMapper::toDomain)
                .toList();
    }

    @Override
    public HorarioPreferencia atualizar(HorarioPreferencia horarioPreferencia) {
        if (horarioPreferencia.getId() == null) {
            throw new IllegalArgumentException("ID do horário é obrigatório para atualização");
        }
        HorarioPreferenciaEntity entity = horarioPreferenciaEntityMapper.toEntity(horarioPreferencia);
        HorarioPreferenciaEntity atualizado = horarioPreferenciaRepository.save(entity);
        return horarioPreferenciaEntityMapper.toDomain(atualizado);
    }

    @Override
    public void deletar(Integer id) {
        if (!horarioPreferenciaRepository.existsById(id)) {
            throw new RuntimeException("Horário não encontrado");
        }
        horarioPreferenciaRepository.deleteById(id);
    }

}