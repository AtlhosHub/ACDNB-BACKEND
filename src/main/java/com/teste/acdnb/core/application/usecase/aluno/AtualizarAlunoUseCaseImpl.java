package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.exception.DataConflictException;
import com.teste.acdnb.core.application.exception.ResourceNotFoundException;
import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AtualizarAlunoUseCaseImpl implements AtualizarAlunoUseCase{
    private final AlunoGateway alunoGateway;

    public AtualizarAlunoUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public Aluno execute(Aluno alunoAtualizado, int id) {
        if(!alunoGateway.existsById(id)){
            throw new ResourceNotFoundException("Aluno não encontrado");
        }

        if(
                (!alunoAtualizado.isMenor() && alunoGateway.existsByEmailIgnoreCaseAndIdIsNot(alunoAtualizado.getEmail().getValue(), id)) ||
                (alunoGateway.existsByCpfAndIdIsNot(alunoAtualizado.getCpf().getValue(), id)) ||
                (alunoGateway.existsByRgAndIdIsNot(alunoAtualizado.getRg(), id))
        ){
            throw new DataConflictException("E-mail, RG ou CPF já cadastrados");
        }

        Aluno aluno = alunoGateway.buscarAlunoPorId(id);
        
        aluno.setNome(alunoAtualizado.getNome());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
        aluno.setCpf(alunoAtualizado.getCpf());
        aluno.setRg(alunoAtualizado.getRg());
        aluno.setNomeSocial(alunoAtualizado.getNomeSocial());
        aluno.setGenero(alunoAtualizado.getGenero());
        aluno.setCelular(alunoAtualizado.getCelular());
        aluno.setTelefone(alunoAtualizado.getTelefone());
        aluno.setNacionalidade(alunoAtualizado.getNacionalidade());
        aluno.setNaturalidade(alunoAtualizado.getNaturalidade());
        aluno.setProfissao(alunoAtualizado.getProfissao());
        aluno.setDeficiencia(alunoAtualizado.getDeficiencia());
        aluno.setAtivo(alunoAtualizado.isAtivo());
        aluno.setAtestado(alunoAtualizado.isAtestado());
        aluno.setAutorizado(alunoAtualizado.isAutorizado());
        aluno.setDataInclusao(alunoAtualizado.getDataInclusao());
        
        Endereco novoEndereco = alunoAtualizado.getEndereco();
        if (novoEndereco != null) {
            Endereco enderecoExistente = alunoGateway.findEndereco(novoEndereco)
                    .orElseGet(() -> alunoGateway.saveEndereco(novoEndereco));
            aluno.setEndereco(enderecoExistente);
        }

        if(alunoAtualizado.isMenor()) {
            List<Responsavel> responsaveisAtualizados = Optional.ofNullable(alunoAtualizado.getResponsaveis()).orElse(List.of())
                    .stream()
                    .map(responsavelNovo -> alunoGateway.findResponsavelPorCpf(responsavelNovo.getCpf().getValue())
                            .map(responsavelExistente -> {
                                responsavelNovo.setId(responsavelExistente.getId());
                                return alunoGateway.saveResponsavel(responsavelNovo);
                            })
                            .orElseGet(() -> alunoGateway.saveResponsavel(responsavelNovo))
                    ).collect(Collectors.toList());
            aluno.setResponsaveis(responsaveisAtualizados);
        }else{
            aluno.setResponsaveis(List.of());
        }

        return alunoGateway.salvarAluno(aluno);
    }
}
