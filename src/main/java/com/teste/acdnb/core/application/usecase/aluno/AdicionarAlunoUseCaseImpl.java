package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;

public class AdicionarAlunoUseCaseImpl implements AdicionarAlunoUseCase {
    private final AlunoGateway alunoGateway;
    public AdicionarAlunoUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public Aluno execute(AlunoDTO alunoDTO) {
        //create a mapper later
        var alunoParaCadastrar = new Aluno();
        alunoParaCadastrar.setNome(Nome.of(alunoDTO.nome()));
        alunoParaCadastrar.setEmail(Email.of(alunoDTO.email()));
        alunoParaCadastrar.setDataNascimento(DataInclusao.of(alunoDTO.dataNascimento()));
        alunoParaCadastrar.setCpf(Cpf.of(alunoDTO.cpf()));
        alunoParaCadastrar.setRg(alunoDTO.rg());
        alunoParaCadastrar.setNomeSocial(Nome.of(alunoDTO.nomeSocial()));
        alunoParaCadastrar.setGenero(alunoDTO.genero());
        alunoParaCadastrar.setCelular(Celular.of(alunoDTO.celular()));
        alunoParaCadastrar.setTelefone(Telefone.of(alunoDTO.telefone()));
        alunoParaCadastrar.setNacionalidade(alunoDTO.nacionalidade());
        alunoParaCadastrar.setNaturalidade(alunoDTO.naturalidade());
        alunoParaCadastrar.setProfissao(alunoDTO.profissao());
        alunoParaCadastrar.setDeficiencia(alunoDTO.deficiencia());
        alunoParaCadastrar.setAtivo(alunoDTO.ativo());
        alunoParaCadastrar.setAtestado(alunoDTO.atestado());
        alunoParaCadastrar.setAutorizado(alunoDTO.autorizado());
        alunoParaCadastrar.setDataInclusao(DataInclusao.of(alunoDTO.dataInclusao()));
//        alunoParaCadastrar.setEndereco(alunoDTO.endereco());
//        alunoParaCadastrar.setResponsaveis(alunoDTO.responsavel());
//        alunoParaCadastrar.setUsuarioInclusao(alunoDTO.usuarioInclusao());

        return alunoGateway.adicionarAluno(alunoParaCadastrar);
    }
}
