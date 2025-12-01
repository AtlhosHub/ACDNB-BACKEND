package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.exception.DataConflictException;
import com.teste.acdnb.core.application.exception.ResourceNotFoundException;
import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.infrastructure.security.ProdutorMensagem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AtualizarAlunoUseCaseImpl implements AtualizarAlunoUseCase {
    private final AlunoGateway alunoGateway;
    private final ProdutorMensagem produtorMensagem;

    public AtualizarAlunoUseCaseImpl(AlunoGateway alunoGateway, ProdutorMensagem produtorMensagem) {
        this.alunoGateway = alunoGateway;
        this.produtorMensagem = produtorMensagem;
    }

    @Override
    public Aluno execute(Aluno alunoAtualizado, int id) {

        if (!alunoGateway.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado");
        }

        Aluno alunoExistente = alunoGateway.buscarAlunoPorId(id);
        String emailAntigo = alunoExistente.getEmail().getValue();

        if ((!alunoAtualizado.isMenor() &&
                alunoGateway.existsByEmailIgnoreCaseAndIdIsNot(alunoAtualizado.getEmail().getValue(), id)) ||
                alunoGateway.existsByCpfAndIdIsNot(alunoAtualizado.getCpf().getValue(), id) ||
                alunoGateway.existsByRgAndIdIsNot(alunoAtualizado.getRg(), id)) {
            throw new DataConflictException("E-mail, RG ou CPF já cadastrados");
        }

        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setEmail(alunoAtualizado.getEmail());
        alunoExistente.setDataNascimento(alunoAtualizado.getDataNascimento());
        alunoExistente.setCpf(alunoAtualizado.getCpf());
        alunoExistente.setRg(alunoAtualizado.getRg());
        alunoExistente.setNomeSocial(alunoAtualizado.getNomeSocial());
        alunoExistente.setGenero(alunoAtualizado.getGenero());
        alunoExistente.setCelular(alunoAtualizado.getCelular());
        alunoExistente.setTelefone(alunoAtualizado.getTelefone());
        alunoExistente.setNacionalidade(alunoAtualizado.getNacionalidade());
        alunoExistente.setNaturalidade(alunoAtualizado.getNaturalidade());
        alunoExistente.setProfissao(alunoAtualizado.getProfissao());
        alunoExistente.setDeficiencia(alunoAtualizado.getDeficiencia());
        alunoExistente.setAtivo(alunoAtualizado.isAtivo());
        alunoExistente.setAtestado(alunoAtualizado.isAtestado());
        alunoExistente.setAutorizado(alunoAtualizado.isAutorizado());
        alunoExistente.setDataInclusao(alunoAtualizado.getDataInclusao());

        Endereco novoEndereco = alunoAtualizado.getEndereco();
        if (novoEndereco != null) {
            Endereco enderecoExistente = alunoGateway.findEndereco(novoEndereco)
                    .orElseGet(() -> alunoGateway.saveEndereco(novoEndereco));
            alunoExistente.setEndereco(enderecoExistente);
        }

        if (alunoAtualizado.isMenor()) {
            List<Responsavel> responsaveisAtualizados = Optional
                    .ofNullable(alunoAtualizado.getResponsaveis())
                    .orElse(List.of())
                    .stream()
                    .map(novoResp -> alunoGateway.findResponsavelPorCpf(novoResp.getCpf().getValue())
                            .map(respExist -> {
                                novoResp.setId(respExist.getId());
                                return alunoGateway.saveResponsavel(novoResp);
                            })
                            .orElseGet(() -> alunoGateway.saveResponsavel(novoResp)))
                    .collect(Collectors.toList());

            alunoExistente.setResponsaveis(responsaveisAtualizados);
        } else {
            alunoExistente.setResponsaveis(List.of());
        }

        Aluno alunoAtualizadoSalvo = alunoGateway.salvarAluno(alunoExistente);

        String novoEmail = alunoAtualizadoSalvo.getEmail().getValue();

        if (!novoEmail.equals(emailAntigo)) {

            String emailContato =
                    alunoAtualizadoSalvo.getResponsaveis() != null &&
                            !alunoAtualizadoSalvo.getResponsaveis().isEmpty()
                            ? alunoAtualizadoSalvo.getResponsaveis().get(0).getEmail().getValue()
                            : novoEmail;

            produtorMensagem.enviarAlunoAtualizado(
                    (long) alunoAtualizadoSalvo.getId(),
                    alunoAtualizadoSalvo.getNome().getValue(),
                    emailContato,
                    emailAntigo
            );
        }

        return alunoAtualizadoSalvo;
    }
}
