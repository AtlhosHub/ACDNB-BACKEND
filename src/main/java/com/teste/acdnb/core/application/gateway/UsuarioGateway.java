package com.teste.acdnb.core.application.gateway;

import com.teste.acdnb.core.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {
    Usuario adicionarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorId(int id);
    Usuario removerUsuarioPorId(int id);
    Optional buscarUsuarioPorEmail(String email);
}
