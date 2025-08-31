package com.teste.acdnb.infrastructure.persistence.jpa.listaEspera;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListaEsperaRepository extends JpaRepository<ListaEsperaEntity, Integer> {
    Optional<ListaEsperaEntity> existsByEmailIgnoreCase(String email);
}
