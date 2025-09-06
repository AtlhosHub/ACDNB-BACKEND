package com.teste.acdnb.infrastructure.persistence.jpa.listaEspera;

import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.infrastructure.persistence.jpa.horarioPreferencia.HorarioPreferenciaEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class ListaEsperaEntityMapper {

    private final UsuarioEntityMapper usuarioEntityMapper;
    private final HorarioPreferenciaEntityMapper horarioPreferenciaEntityMapper;

    public ListaEsperaEntityMapper(UsuarioEntityMapper usuarioEntityMapper,
                                   HorarioPreferenciaEntityMapper horarioPreferenciaEntityMapper) {
        this.usuarioEntityMapper = usuarioEntityMapper;
        this.horarioPreferenciaEntityMapper = horarioPreferenciaEntityMapper;
    }

    public ListaEsperaEntity toEntity(ListaEspera listaEspera) {
        if (listaEspera == null) return null;

        return new ListaEsperaEntity(
                listaEspera.getId(),
                listaEspera.getNome() != null ? listaEspera.getNome().getValue() : null,
                listaEspera.getEmail() != null ? listaEspera.getEmail().getValue() : null,
                listaEspera.getCelular() != null ? listaEspera.getCelular().getValue() : null,
                listaEspera.getDataNascimento() != null ? listaEspera.getDataNascimento().getValue() : null,
                listaEspera.getNomeSocial() != null ? listaEspera.getNomeSocial().getValue() : null,
                listaEspera.getGenero(),
                listaEspera.getTelefone() != null ? listaEspera.getTelefone().getValue() : null,
                listaEspera.getDataInclusao() != null ? listaEspera.getDataInclusao().getValue() : null,
                listaEspera.getDataInteresse() != null ? listaEspera.getDataInteresse().getValue() : null,
                listaEspera.getHorarioPref() != null ? horarioPreferenciaEntityMapper.toEntity(listaEspera.getHorarioPref()) : null,
                listaEspera.getUsuarioInclusao() != null ? usuarioEntityMapper.toEntity(listaEspera.getUsuarioInclusao()) : null
        );
    }

    public ListaEspera toDomain(ListaEsperaEntity entity) {
        if (entity == null) return null;

        return new ListaEspera(
                entity.getId(),
                entity.getNome() != null ? Nome.of(entity.getNome()) : null,
                entity.getEmail() != null ? Email.of(entity.getEmail()) : null,
                entity.getDataInteresse() != null ? DataInclusao.of(entity.getDataInteresse()) : null,
                entity.getCelular() != null ? Celular.of(entity.getCelular()) : null,
                entity.getNomeSocial() != null ? Nome.of(entity.getNomeSocial()) : null,
                entity.getGenero(),
                entity.getDataNascimento() != null ? DataInclusao.of(entity.getDataNascimento()) : null,
                entity.getTelefone() != null ? Telefone.of(entity.getTelefone()) : null,
                entity.getDataInclusao() != null ? DataInclusao.of(entity.getDataInclusao()) : null,
                usuarioEntityMapper.toDomain(entity.getUsuarioInclusao()),
                entity.getHorarioPreferencia() != null ? horarioPreferenciaEntityMapper.toDomain(entity.getHorarioPreferencia()) : null
        );
    }

    public void updateEntityFromDomain(ListaEspera domain, ListaEsperaEntity entity) {
        if (domain == null || entity == null) return;

        if (domain.getNome() != null) entity.setNome(domain.getNome().getValue());
        if (domain.getEmail() != null) entity.setEmail(domain.getEmail().getValue());
        if (domain.getCelular() != null) entity.setCelular(domain.getCelular().getValue());
        if (domain.getNomeSocial() != null) entity.setNomeSocial(domain.getNomeSocial().getValue());
        if (domain.getGenero() != null) entity.setGenero(domain.getGenero());
        if (domain.getTelefone() != null) entity.setTelefone(domain.getTelefone().getValue());
        if (domain.getDataInclusao() != null) entity.setDataInclusao(domain.getDataInclusao().getValue());
        if (domain.getDataInteresse() != null) entity.setDataInteresse(domain.getDataInteresse().getValue());
        if (domain.getDataNascimento() != null) entity.setDataNascimento(domain.getDataNascimento().getValue());
        if (domain.getHorarioPref() != null) entity.setHorarioPreferencia(horarioPreferenciaEntityMapper.toEntity(domain.getHorarioPref()));
        if (domain.getUsuarioInclusao() != null) entity.setUsuarioInclusao(usuarioEntityMapper.toEntity(domain.getUsuarioInclusao()));
    }
}