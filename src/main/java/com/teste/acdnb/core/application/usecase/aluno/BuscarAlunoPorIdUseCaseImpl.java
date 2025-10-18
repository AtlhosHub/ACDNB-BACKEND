package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoInfoDTO;

public class BuscarAlunoPorIdUseCaseImpl implements BuscarAlunoPorIdUseCase{
    private final AlunoGateway alunoGateway;

    public BuscarAlunoPorIdUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public AlunoInfoDTO execute(int id) {
        if(!alunoGateway.existsById(id)){
            throw new RuntimeException("Aluno não encontrado");
        }

        Aluno aluno = alunoGateway.buscarAlunoPorId(id);

        AlunoInfoDTO alunoInfoDTO = toDTO(aluno);

        return alunoInfoDTO;
    }

    public AlunoInfoDTO toDTO(Aluno aluno){
        return new AlunoInfoDTO(
                aluno.getId(),
                aluno.getNome().getValue(),
                aluno.getEmail().getValue(),
                aluno.getDataNascimento().getValue(),
                aluno.getCpf().getValue(),
                aluno.getRg(),
                aluno.getNomeSocialValue(),
                aluno.getGenero(),
                aluno.getCelular().getValue(),
                aluno.getNacionalidade(),
                aluno.getNaturalidade(),
                aluno.getTelefoneValue(),
                aluno.getProfissao(),
                aluno.isAtivo(),
                aluno.isAtestado(),
                aluno.getDeficiencia(),
                aluno.isAutorizado(),
                aluno.getDataInclusao().getValue(),
                aluno.getEndereco(),
                aluno.getResponsaveis()
//                aluno.getUsuarioInclusao.getId()
        );
    }
}
