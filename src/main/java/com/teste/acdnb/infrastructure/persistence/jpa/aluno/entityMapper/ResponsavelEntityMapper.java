package com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper;

import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.ResponsavelEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResponsavelEntityMapper {
    private AlunoEntityMapper alunoEntityMapper;

    public ResponsavelEntityMapper(@Lazy AlunoEntityMapper alunoEntityMapper) {
        this.alunoEntityMapper = alunoEntityMapper;
    }

    public List<ResponsavelEntity> toEntity(List<Responsavel> responsaveis) {
        List<ResponsavelEntity> listaResponsaveis = new ArrayList<>();
        for (Responsavel r : responsaveis) {
            ResponsavelEntity responsavelEntity = new ResponsavelEntity(
                r.getId(),
                r.getNome().getValue(),
                r.getCpf().getValue(),
                r.getCelular().getValue(),
                r.getEmail().getValue(),
                r.getRg(),
                r.getTelefone().getValue(),
                r.getNomeSocial().getValue(),
                r.getGenero(),
                r.getProfissao()
//                alunoEntityMapper.toEntityList(r.getAlunos())
            );
            listaResponsaveis.add(responsavelEntity);
        }

        return listaResponsaveis;
    }

    public List<Responsavel> toDomain(List<ResponsavelEntity> responsaveis) {
        List<Responsavel> listaResponsaveis = new ArrayList<>();
        for (ResponsavelEntity r : responsaveis){
            Responsavel responsavel = new Responsavel(
                r.getId(),
                Nome.of(r.getNome()),
                Cpf.of(r.getCpf()),
                Celular.of(r.getCelular()),
                Email.of(r.getEmail()),
                r.getRg(),
                Telefone.of(r.getTelefone()),
                Nome.of(r.getNomeSocial()),
                r.getGenero(),
                r.getProfissao()
//                alunoEntityMapper.toDomainList(r.getAlunos())
            );
            listaResponsaveis.add(responsavel);
        }

        return listaResponsaveis;
    }
}
