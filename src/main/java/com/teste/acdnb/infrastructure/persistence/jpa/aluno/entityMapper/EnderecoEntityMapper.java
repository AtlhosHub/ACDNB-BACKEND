package com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper;

import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.valueobject.Cep;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.EnderecoEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EnderecoEntityMapper {
    private AlunoEntityMapper alunoEntityMapper;

    public EnderecoEntityMapper(@Lazy AlunoEntityMapper alunoEntityMapper) {
        this.alunoEntityMapper = alunoEntityMapper;
    }

    public EnderecoEntity toEntity(Endereco endereco) {
        return new EnderecoEntity(
            endereco.getId(),
            endereco.getLogradouro(),
            endereco.getNumLog(),
            endereco.getBairro(),
            endereco.getCidade(),
            endereco.getEstado(),
            endereco.getCep().getValue()
//            alunoEntityMapper.toEntityList(endereco.getAlunos())
        );
    }

    public Endereco toDomain(EnderecoEntity enderecoEntity) {
        return new Endereco(
            enderecoEntity.getId(),
            enderecoEntity.getLogradouro(),
            enderecoEntity.getNumLog(),
            enderecoEntity.getBairro(),
            enderecoEntity.getCidade(),
            enderecoEntity.getEstado(),
            Cep.of(enderecoEntity.getCep())
//            alunoEntityMapper.toDomainList(enderecoEntity.getAlunos())
        );
    }
}
