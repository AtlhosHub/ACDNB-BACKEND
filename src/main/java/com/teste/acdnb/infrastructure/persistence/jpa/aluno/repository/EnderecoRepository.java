package com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository;

import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {
    Optional<EnderecoEntity> findByLogradouroAndNumLogAndBairroAndCidadeAndCepAndEstado(String logradouro, String numLog, String bairro, String cidade, String cep, String estado);
}
