package com.teste.acdnb.infrastructure.persistence.jpa.usuario;

import com.teste.acdnb.core.domain.usuario.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByEmailIgnoreCase(String email);

    List<UsuarioEntity> findAll();

    Optional<UsuarioEntity> findById(int id);

    List<UsuarioEntity> findByUsuarioInclusaoId(int id);

    List<UsuarioEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
