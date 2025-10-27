package com.teste.acdnb.infrastructure.persistence.jpa.mensalidade;

import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.entities.comprovante.ComprovanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprovanteRepository extends JpaRepository<ComprovanteEntity, Integer> {
}