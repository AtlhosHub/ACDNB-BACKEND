package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoMapperUtil;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.EnderecoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.ResponsavelEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.AlunoRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.EnderecoRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.ResponsavelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlunoRepositoryGateway implements AlunoGateway {
    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;
    private final ResponsavelRepository responsavelRepository;

    private final AlunoEntityMapper alunoEntityMapper;

    public AlunoRepositoryGateway(AlunoRepository alunoRepository, EnderecoRepository enderecoRepository, ResponsavelRepository responsavelRepository, AlunoEntityMapper alunoEntityMapper) {
        this.alunoRepository = alunoRepository;
        this.enderecoRepository = enderecoRepository;
        this.responsavelRepository = responsavelRepository;
        this.alunoEntityMapper = alunoEntityMapper;
    }

    @Override
    public Aluno adicionarAluno(Aluno aluno){
        return AlunoEntityMapper.toDomain(
                alunoRepository.save(
                        AlunoEntityMapper.toEntity(aluno)
                )
        );
    }

    @Override
    public boolean existsByEmailIgnoreCaseOrCpfOrRg(String email, String cpf, String rg){
        return alunoRepository.existsByEmailIgnoreCaseOrCpfOrRg(email, cpf, rg);
    }

    @Override
    public boolean existsByCpfOrRg(String cpf, String rg){
        return alunoRepository.existsByCpfOrRg(cpf, rg);
    }

    @Override
    public Optional<Endereco> findEndereco(Endereco endereco){
        return enderecoRepository.findByLogradouroAndNumLogAndBairroAndCidadeAndCepAndEstado(
                endereco.getLogradouro(),
                endereco.getNumLog(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getCep().getValue(),
                endereco.getEstado()
        ).map(EnderecoEntityMapper::toDomain);
    }

    @Override
    public Endereco saveEndereco(Endereco endereco){
        return EnderecoEntityMapper.toDomain(enderecoRepository.save(EnderecoEntityMapper.toEntity(endereco)));
    }

    @Override
    public Optional<Responsavel> findResponsavelPorCpf(String cpf){
        return responsavelRepository.findByCpf(cpf).map(ResponsavelEntityMapper::toDomain);
    }

    @Override
    public Responsavel saveResponsavel(Responsavel responsavel){
        return ResponsavelEntityMapper.toDomain(responsavelRepository.save(ResponsavelEntityMapper.toEntity(responsavel)));
    }

    @Override
    public List<Aluno> listarAlunos(){
        return AlunoMapperUtil.toDomainList(alunoRepository.findAll(Sort.by(Sort.Order.asc("nome").ignoreCase())), alunoEntityMapper);
    }

    @Override
    public boolean existsById(int id){
        return alunoRepository.existsById(id);
    }

    @Override
    public void deletarAluno(int id){
        alunoRepository.deleteById(id);
    }

    @Override
    public Aluno buscarAlunoPorId(int id){
        return AlunoEntityMapper.toDomain(alunoRepository.findById(id).get());
    }

    @Override
    public Aluno atualizarAluno(Aluno aluno, int id) {
        return new Aluno();
    }
}
