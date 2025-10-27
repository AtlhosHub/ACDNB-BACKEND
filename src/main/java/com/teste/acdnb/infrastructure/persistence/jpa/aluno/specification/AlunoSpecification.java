package com.teste.acdnb.infrastructure.persistence.jpa.aluno.specification;

import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import org.springframework.data.jpa.domain.Specification;

public class AlunoSpecification {
    private static final String NOME = "nome";
    private static final String ATIVO = "ativo";

    private AlunoSpecification() {}

    public static Specification<AlunoEntity> filtrarPor(ListarAlunosMensalidadeFilter listarAlunosMensalidadeFilter){
        return hasNomeLike(listarAlunosMensalidadeFilter.nome()).or(hasNomeSocialLike(listarAlunosMensalidadeFilter.nome())).and(hasPresenteEqual(listarAlunosMensalidadeFilter.ativo()));
    }

    private static Specification<AlunoEntity> hasNomeLike(String nome) {
        return ((root, query, cb) -> nome == null || nome.isEmpty() ? cb.conjunction() : cb.like(cb.lower(root.get(NOME)), "%" + nome.toLowerCase() + "%"));
    }

    private static Specification<AlunoEntity> hasPresenteEqual(Boolean ativo) {
        return ((root, query, cb) -> ativo == null ? cb.conjunction() : cb.equal(root.get(ATIVO), ativo));
    }

    private static Specification<AlunoEntity> hasNomeSocialLike(String nomeSocial) {
        return ((root, query, cb) -> nomeSocial == null || nomeSocial.isEmpty() ? cb.conjunction() : cb.like(cb.lower(root.get(NOME)), "%" + nomeSocial.toLowerCase() + "%"));
    }
}
