package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioRepositoryGateway implements UsuarioGateway {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public UsuarioRepositoryGateway(UsuarioRepository usuarioRepository, UsuarioEntityMapper usuarioEntityMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }


    @Override
    public Usuario adicionarUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioEntityMapper.toEntity(usuario);
        UsuarioEntity novoUsuario = usuarioRepository.save(usuarioEntity);

        return usuarioEntityMapper.toDomain(novoUsuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(int id) {
        return usuarioRepository.findById(id)
                .map(usuarioEntityMapper::toDomain);
    }

    @Override
    public void removerUsuarioPorId(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmailIgnoreCase(email)
                .map(usuarioEntityMapper::toDomain);
    }

    @Override
    public Usuario atualizarUsuario(Usuario usuario) {
        UsuarioEntity entity = usuarioEntityMapper.toEntity(usuario);
        UsuarioEntity atualizado = usuarioRepository.save(entity);
        return usuarioEntityMapper.toDomain(atualizado);
    }

    @Override
    public List<Usuario> buscarUsuariosPorUsuarioInclusao(Usuario usuario) {
        return usuarioRepository.findByUsuarioInclusaoId(usuario.getId())
                .stream()
                .map(usuarioEntityMapper::toDomain)
                .toList();
    }
}
