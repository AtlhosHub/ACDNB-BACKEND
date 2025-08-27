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
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNome().getValue(),
                usuario.getEmail().getValue(),
                usuario.getSenha().getValue(),
                usuario.getCelular().getValue(),
                usuario.getDataNascimento().getValue(),
                usuario.getNomeSocial().getValue(),
                usuario.getGenero(),
                usuario.getTelefone().getValue(),
                usuario.getCargo(),
                usuario.getDataInclusao().getValue()
//                toEntity(usuario.getUsuarioInclusao()),
//                toEntityList(usuario.getUsuariosCadastrados())
        );
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
                entity.getDataNascimento() != null ? DataInclusao.of(entity.getDataNascimento()) : null,
                entity.getNomeSocial() != null ? Nome.of(entity.getNomeSocial()) : null,
                entity.getGenero(),
                entity.getTelefone() != null ? Telefone.of(entity.getTelefone()) : null,
                entity.getCargo(),
                entity.getDataInclusao() != null ? DataInclusao.of(entity.getDataInclusao()) : null
//                toDomain(entity.getUsuarioInclusao()),
//                toDomainList(entity.getUsuariosCadastrados())
        );
    }

    private List<Usuario> toDomainList(List<UsuarioEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }
}
