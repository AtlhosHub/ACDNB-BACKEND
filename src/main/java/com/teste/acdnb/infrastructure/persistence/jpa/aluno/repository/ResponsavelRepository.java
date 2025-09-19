package com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository;

import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.ResponsavelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<ResponsavelEntity, Integer> {
    Optional<ResponsavelEntity> findByCpf(String cpf);
}
