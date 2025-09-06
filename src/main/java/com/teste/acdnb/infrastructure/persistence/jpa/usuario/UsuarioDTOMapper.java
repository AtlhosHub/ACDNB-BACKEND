package com.teste.acdnb.infrastructure.persistence.jpa.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioResponseDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioListaDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOMapper {

    public static UsuarioListaDTO toListaDTO(Usuario usuario) {
        String nomeExibido = (usuario.getNomeSocial() == null || usuario.getNomeSocial().getValue().trim().isEmpty())
                ? usuario.getNome().getValue()
                : usuario.getNomeSocial().getValue();

        return new UsuarioListaDTO(usuario.getId(), nomeExibido);
    }

    public static UsuarioResponseDTO toInfoDTO(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioResponseDTO(
                usuario.getNome().getValue(),
                usuario.getEmail().getValue(),
                usuario.getCelular().getValue(),
                usuario.getDataNascimento().getValue(),
                usuario.getNomeSocial().getValue(),
                usuario.getGenero(),
                usuario.getTelefone().getValue(),
                usuario.getCargo()
        );
    }

}
