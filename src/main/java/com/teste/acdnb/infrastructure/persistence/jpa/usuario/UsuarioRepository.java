package com.teste.acdnb.infrastructure.persistence.jpa.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> existsByEmailIgnoreCase(String email);
}
