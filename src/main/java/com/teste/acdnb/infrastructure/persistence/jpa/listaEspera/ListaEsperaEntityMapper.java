package com.teste.acdnb.infrastructure.persistence.jpa.listaEspera;

import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.core.domain.listaEspera.HorarioPreferencia;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListaEsperaEntityMapper {

    private final UsuarioEntityMapper usuarioEntityMapper;

    public ListaEsperaEntityMapper(UsuarioEntityMapper usuarioEntityMapper) {
        this.usuarioEntityMapper = usuarioEntityMapper;
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
                listaEspera.getHorarioPref() != null ? listaEspera.getHorarioPref().getDiaSemana() : null,
                listaEspera.getUsuarioInclusao() != null ? usuarioEntityMapper.toEntity(listaEspera.getUsuarioInclusao()) : null
        );
    }

    private List<ListaEsperaEntity> toEntityList(List<ListaEspera> listas) {
        if (listas == null) return null;
        return listas.stream()
                .map(this::toEntity)
                .toList();
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
                entity.getHorarioPreferencia() != null ? buildHorarioPrefFromString(entity.getHorarioPreferencia()) : null
        );
    }

    private List<ListaEspera> toDomainList(List<ListaEsperaEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }

    private HorarioPreferencia buildHorarioPrefFromString(String diaSemana) {
        if (diaSemana == null) return null;
        HorarioPreferencia hp = new HorarioPreferencia();
        hp.setDiaSemana(diaSemana);
        return hp;
    }
}
