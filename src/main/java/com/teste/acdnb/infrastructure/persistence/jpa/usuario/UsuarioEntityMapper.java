package com.teste.acdnb.infrastructure.persistence.jpa.usuario;

import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.core.domain.usuario.valueobject.Senha;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UsuarioEntityMapper {
    public UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNome(usuario.getNome() != null ? usuario.getNome().getValue() : null);
        entity.setEmail(usuario.getEmail() != null ? usuario.getEmail().getValue() : null);
        entity.setSenha(usuario.getSenha() != null ? usuario.getSenha().getValue() : null);
        entity.setCelular(usuario.getCelular() != null ? usuario.getCelular().getValue() : null);
        entity.setDataNascimento(usuario.getDataNascimento() != null ? usuario.getDataNascimento().getValue() : null);
        entity.setNomeSocial(usuario.getNomeSocial() != null ? usuario.getNomeSocial().getValue() : null);
        entity.setGenero(usuario.getGenero());
        entity.setTelefone(usuario.getTelefone() != null ? usuario.getTelefone().getValue() : null);
        entity.setCargo(usuario.getCargo());
        entity.setDataInclusao(usuario.getDataInclusao() != null ? usuario.getDataInclusao().getValue() : null);

        if (usuario.getUsuarioInclusao() != null) {
            UsuarioEntity usuarioInclusaoEntity = new UsuarioEntity();
            usuarioInclusaoEntity.setId(usuario.getUsuarioInclusao().getId());
            entity.setUsuarioInclusao(usuarioInclusaoEntity);
        }

        return entity;
    }

    private List<UsuarioEntity> toEntityList(List<Usuario> usuarios) {
        if (usuarios == null) return null;
        return usuarios.stream()
                .map(this::toEntity)
                .toList();
    }

    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;
        return new Usuario(
                entity.getId(),
                entity.getNome() != null ? Nome.of(entity.getNome()) : null,
                entity.getEmail() != null ? Email.of(entity.getEmail()) : null,
                entity.getSenha() != null ? Senha.of(entity.getSenha()) : null,
                entity.getCelular() != null ? Celular.of(entity.getCelular()) : null,
                entity.getDataNascimento() != null ? DataNascimento.of(entity.getDataNascimento()) : null,
                entity.getNomeSocial() != null ? NomeSocial.of(entity.getNomeSocial(), entity.getNome()) : null,
                entity.getGenero(),
                entity.getTelefone() != null ? Telefone.of(entity.getTelefone()) : null,
                entity.getCargo(),
                entity.getDataInclusao() != null ? DataInclusao.of(entity.getDataInclusao()) : null,
                entity.getUsuarioInclusao() != null ? toDomain(entity.getUsuarioInclusao()) : null
        );
    }

    private List<Usuario> toDomainList(List<UsuarioEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }
}
