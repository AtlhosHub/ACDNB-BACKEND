package com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlunoEntityMapper {
    private UsuarioEntityMapper usuarioEntityMapper;
    private EnderecoEntityMapper enderecoEntityMapper;
    private ResponsavelEntityMapper responsavelEntityMapper;

    public AlunoEntityMapper(UsuarioEntityMapper usuarioEntityMapper, EnderecoEntityMapper enderecoEntityMapper, ResponsavelEntityMapper responsavelEntityMapper) {
        this.usuarioEntityMapper = usuarioEntityMapper;
        this.enderecoEntityMapper = enderecoEntityMapper;
        this.responsavelEntityMapper = responsavelEntityMapper;
    }

    public AlunoEntity toEntity(Aluno aluno) {
        return new AlunoEntity(
            aluno.getId(),
            aluno.getNome().getValue(),
            aluno.getEmail().getValue(),
            aluno.getDataNascimento().getValue().toLocalDate(),
            aluno.getCpf().getValue(),
            aluno.getRg(),
            aluno.getNomeSocial().getValue(),
            aluno.getGenero(),
            aluno.getCelular().getValue(),
            aluno.getTelefone().getValue(),
            aluno.getNacionalidade(),
            aluno.getNaturalidade(),
            aluno.getProfissao(),
            aluno.getDeficiencia(),
            aluno.isAtivo(),
            aluno.isAtestado(),
            aluno.isAutorizado(),
            aluno.getDataInclusao().getValue()
//            enderecoEntityMapper.toEntity(aluno.getEndereco()),
//            aluno.getResponsaveis() != null ? responsavelEntityMapper.toEntity(aluno.getResponsaveis()) : null,
//            aluno.getUsuarioInclusao() != null ? usuarioEntityMapper.toEntity(aluno.getUsuarioInclusao()) : null
        );
    }

    public Aluno toDomain(AlunoEntity entity) {
        if (entity == null) return null;

        return new Aluno(
            entity.getId(),
            Nome.of(entity.getNome()),
            Email.of(entity.getEmail()),
            DataInclusao.of(entity.getDataNascimento().atStartOfDay()),
            Cpf.of(entity.getCpf()),
            entity.getRg(),
            Nome.of(entity.getNomeSocial()),
            entity.getGenero(),
            Celular.of(entity.getCelular()),
            Telefone.of(entity.getTelefone()),
            entity.getNacionalidade(),
            entity.getNaturalidade(),
            entity.getProfissao(),
            entity.getDeficiencia(),
            entity.isAtivo(),
            entity.isAtestado(),
            entity.isAutorizado(),
            DataInclusao.of(entity.getDataInclusao())
//            enderecoEntityMapper.toDomain(entity.getEndereco()),
//            responsavelEntityMapper.toDomain(entity.getResponsaveis()),
//            usuarioEntityMapper.toDomain(entity.getUsuarioInclusao())
        );
    }

    public List<AlunoEntity> toEntityList(List<Aluno> alunos){
        return alunos.stream().map(this::toEntity).toList();
    }

    public List<Aluno> toDomainList(List<AlunoEntity> alunos){
        return alunos.stream().map(this::toDomain).toList();
    }
}
