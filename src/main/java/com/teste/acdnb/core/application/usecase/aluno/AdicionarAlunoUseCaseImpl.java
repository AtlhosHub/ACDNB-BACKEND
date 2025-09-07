package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AdicionarAlunoUseCaseImpl implements AdicionarAlunoUseCase {
    private final AlunoGateway alunoGateway;
    public AdicionarAlunoUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public Aluno execute(AlunoDTO alunoDTO) {
        var alunoParaCadastrar = toAluno(alunoDTO);

        if (!alunoParaCadastrar.isMenor()) {
            if (alunoGateway.existsByEmailIgnoreCaseOrCpfOrRg(alunoParaCadastrar.getEmail().getValue(), alunoParaCadastrar.getCpf().getValue(), alunoParaCadastrar.getRg())) {
                throw new IllegalArgumentException("E-mail, RG ou CPF já cadastrados");
            }
        } else {
            if (alunoGateway.existsByCpfOrRg(alunoParaCadastrar.getCpf().getValue(), alunoParaCadastrar.getRg())) {
                throw new IllegalArgumentException("RG ou CPF já cadastrados");
            }
        }

        Endereco endereco = alunoGateway.findEndereco(alunoParaCadastrar.getEndereco())
                .orElseGet(() -> alunoGateway.saveEndereco(alunoParaCadastrar.getEndereco()));
        alunoParaCadastrar.setEndereco(endereco);

        if (alunoParaCadastrar.isMenor()) {
            if (alunoDTO.responsaveis() == null || alunoDTO.responsaveis().isEmpty()) {
                throw new IllegalArgumentException("Aluno menor de idade deve ter ao menos um responsável cadastrado");
            }

            List<Responsavel> responsaveis = alunoParaCadastrar.getResponsaveis().stream()
                    .map(r -> alunoGateway.findResponsavelPorCpf(r.getCpf().getValue())
                            .orElseGet(() -> alunoGateway.saveResponsavel(r)))
                    .collect(Collectors.toList());
            alunoParaCadastrar.setResponsaveis(responsaveis);
        }

        Aluno alunoCadastrado = alunoGateway.adicionarAluno(alunoParaCadastrar);

        return alunoCadastrado;
    }

    public Aluno toAluno(AlunoDTO alunoDTO){
        var aluno = new Aluno();

        aluno.setNome(Nome.of(alunoDTO.nome()));
        aluno.setEmail(Email.of(alunoDTO.email()));
        aluno.setDataNascimento(DataInclusao.of(alunoDTO.dataNascimento()));
        aluno.setCpf(Cpf.of(alunoDTO.cpf()));
        aluno.setRg(alunoDTO.rg());
        aluno.setNomeSocial(Nome.of(alunoDTO.nomeSocial()));
        aluno.setGenero(alunoDTO.genero());
        aluno.setCelular(Celular.of(alunoDTO.celular()));
        aluno.setTelefone(Telefone.of(alunoDTO.telefone()));
        aluno.setNacionalidade(alunoDTO.nacionalidade());
        aluno.setNaturalidade(alunoDTO.naturalidade());
        aluno.setProfissao(alunoDTO.profissao());
        aluno.setDeficiencia(alunoDTO.deficiencia());
        aluno.setAtivo(alunoDTO.ativo());
        aluno.setAtestado(alunoDTO.atestado());
        aluno.setAutorizado(alunoDTO.autorizado());
        aluno.setDataInclusao(DataInclusao.of(alunoDTO.dataInclusao()));
        aluno.setEndereco(alunoDTO.endereco());
        aluno.setResponsaveis(alunoDTO.responsaveis());
//        aluno.setUsuarioInclusao(alunoDTO.usuarioInclusao());

        return aluno;
    }
}
