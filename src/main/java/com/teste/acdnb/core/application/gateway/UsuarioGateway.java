package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioFiltroDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {
    Usuario adicionarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Optional <Usuario> buscarUsuarioPorId(int id);
    void removerUsuarioPorId(int id);
    Optional<Usuario> buscarUsuarioPorEmail(String email);
    Usuario atualizarUsuario(Usuario usuario);
 //   List<Usuario> buscarUsuariosPorUsuarioInclusao(Usuario usuario);
    List<Usuario> buscarUsuariosPorNome(UsuarioFiltroDTO usuarioFiltroDTO);
    Optional<Usuario> buscarPorTokenRecuperacao(String token);
    void atualizarTokenRecuperacao(String email, String token, LocalDateTime expiracao);
}
