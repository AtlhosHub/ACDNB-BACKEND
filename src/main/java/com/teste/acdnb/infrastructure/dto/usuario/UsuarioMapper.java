package com.teste.acdnb.infrastructure.dto.usuario;

import com.teste.acdnb.core.domain.shared.valueobject.Email;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.core.domain.usuario.valueobject.Senha;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioMapper {
    @Schema(description = "Converte um DTO de login de usuário para uma entidade de usuário")
    public static Usuario of(UsuarioLoginDTO usuarioLoginDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(Email.of(usuarioLoginDto.getEmail()));
        usuario.setSenha(Senha.of(usuarioLoginDto.getSenha()));

        return usuario;
    }

    @Schema(description = "Converte uma entidade de usuário e um token para um DTO de token de usuário")
    public static UsuarioTokenDTO of(Usuario usuario, String token) {
        UsuarioTokenDTO usuarioTokenDto = new UsuarioTokenDTO();

        usuarioTokenDto.setId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail().getValue());
        usuarioTokenDto.setNome((usuario.getNomeSocial() == null || usuario.getNomeSocial().getValue().trim().isEmpty()) ? usuario.getNome().getValue() : usuario.getNomeSocial().getValue());
        usuarioTokenDto.setToken(token);
        usuarioTokenDto.setUsuarioInclusao(usuario.getUsuarioInclusao() != null ? usuario.getUsuarioInclusao().getId() : null);

        return usuarioTokenDto;
    }
}
