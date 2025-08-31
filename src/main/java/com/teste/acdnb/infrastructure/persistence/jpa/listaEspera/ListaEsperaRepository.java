package com.teste.acdnb.infrastructure.persistence.jpa.listaEspera;

import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ListaEsperaRepository extends JpaRepository<ListaEsperaEntity, Integer> {
    Optional<ListaEsperaEntity> existsByEmailIgnoreCase(String email);
}
