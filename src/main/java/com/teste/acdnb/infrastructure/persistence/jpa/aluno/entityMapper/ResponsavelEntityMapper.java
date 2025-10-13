package com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper;

import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.ResponsavelEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResponsavelEntityMapper {

    public static ResponsavelEntity toEntity(Responsavel responsavel){
        return new ResponsavelEntity(
                responsavel.getId(),
                responsavel.getNome().getValue(),
                responsavel.getCpf().getValue(),
                responsavel.getCelular().getValue(),
                responsavel.getEmail().getValue(),
                responsavel.getRg(),
                responsavel.getTelefone().getValue(),
                responsavel.getNomeSocial().getValue(),
                responsavel.getGenero(),
                responsavel.getProfissao(),
                null
//                AlunoMapperUtil.toEntityList(responsavel.getAlunos(), new AlunoEntityMapper(null, null, this))
        );
    }

    public static List<ResponsavelEntity> toEntityList(List<Responsavel> responsaveis) {
        List<ResponsavelEntity> listaResponsaveis = new ArrayList<>();
        for (Responsavel r : responsaveis) {
            ResponsavelEntity responsavelEntity = toEntity(r);
            listaResponsaveis.add(responsavelEntity);
        }

        return listaResponsaveis;
    }

    public static Responsavel toDomain(ResponsavelEntity responsavel) {
        return new Responsavel(
                responsavel.getId(),
                Nome.of(responsavel.getNome()),
                Cpf.of(responsavel.getCpf()),
                Celular.of(responsavel.getCelular()),
                Email.of(responsavel.getEmail()),
                responsavel.getRg(),
                Telefone.of(responsavel.getTelefone()),
                NomeSocial.of(responsavel.getNomeSocial(), responsavel.getNome()),
                responsavel.getGenero(),
                responsavel.getProfissao(),
                null
//                AlunoMapperUtil.toDomainList(responsavel.getAlunos(), new AlunoEntityMapper(null, null, this))
        );
    }

    public static List<Responsavel> toDomainList(List<ResponsavelEntity> responsaveis) {
        List<Responsavel> listaResponsaveis = new ArrayList<>();
        for (ResponsavelEntity r : responsaveis){
            Responsavel responsavel = toDomain(r);
            listaResponsaveis.add(responsavel);
        }

        return listaResponsaveis;
    }
}
