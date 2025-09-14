package com.teste.acdnb.infrastructure.dto.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UsuarioDetalhesDTO implements UserDetails {
    @Schema(description = "Nome do usuário", example = "João Silva")
    private final String nome;

    @Schema(description = "E-mail do usuário", example = "joao.silva@email.com")
    private final String email;

    @Schema(description = "Senha do usuário", example = "senha123")
    private final String senha;

    public UsuarioDetalhesDTO(Usuario usuario) {
        this.nome = usuario.getNome().getValue();
        this.email = usuario.getEmail().getValue();
        this.senha = usuario.getSenha().getValue();
    }

    @Override
    @Schema(description = "Autoridades concedidas ao usuário", example = "[]")
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @Schema(description = "Senha do usuário", example = "senha123")
    public String getPassword() {
        return senha;
    }

    @Override
    @Schema(description = "E-mail do usuário usado como nome de usuário", example = "joao.silva@email.com")
    public String getUsername() {
        return email;
    }

    @Override
    @Schema(description = "Indica se a conta do usuário está expirada", example = "true")
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Schema(description = "Indica se a conta do usuário está bloqueada", example = "true")
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Schema(description = "Indica se as credenciais do usuário estão expiradas", example = "true")
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Schema(description = "Indica se o usuário está habilitado", example = "true")
    public boolean isEnabled() {
        return true;
    }
}
