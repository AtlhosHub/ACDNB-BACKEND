package com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper;

import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class AlunoEntityMapper {
    private final UsuarioEntityMapper usuarioEntityMapper;

    public AlunoEntityMapper(UsuarioEntityMapper usuarioEntityMapper) {
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    public static AlunoEntity toEntity(Aluno aluno) {
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
            aluno.getDataInclusao().getValue(),
            EnderecoEntityMapper.toEntity(aluno.getEndereco()),
            aluno.isMenor() ? ResponsavelEntityMapper.toEntityList(aluno.getResponsaveis()) : null,
            MensalidadeEntityMapper.toEntityList(aluno.getMensalidades())
//            aluno.getUsuarioInclusao() != null ? usuarioEntityMapper.toEntity(aluno.getUsuarioInclusao()) : null
        );
    }

    public static Aluno toDomain(AlunoEntity entity) {
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
            DataInclusao.of(entity.getDataInclusao()),
            EnderecoEntityMapper.toDomain(entity.getEndereco()),
            entity.isMenor() ? ResponsavelEntityMapper.toDomainList(entity.getResponsaveis()) : null,
            MensalidadeEntityMapper.toDomainList(entity.getMensalidades())
//            usuarioEntityMapper.toDomain(entity.getUsuarioInclusao())
        );
    }
}
