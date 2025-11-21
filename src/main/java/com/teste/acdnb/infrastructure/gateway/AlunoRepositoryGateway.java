package com.teste.acdnb.infrastructure.gateway;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.aluno.Endereco;
import com.teste.acdnb.core.domain.aluno.Responsavel;
import com.teste.acdnb.core.domain.shared.valueobject.Email;
import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entity.AlunoEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.AlunoMapperUtil;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.EnderecoEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.entityMapper.ResponsavelEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.AlunoRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.EnderecoRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.repository.ResponsavelRepository;
import com.teste.acdnb.infrastructure.persistence.jpa.aluno.specification.AlunoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
    public Aluno salvarAluno(Aluno aluno){
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
    public List<Aluno> listarAlunosFiltro(ListarAlunosMensalidadeFilter filter) {
        Specification<AlunoEntity> spec = AlunoSpecification.filtrarPor(filter);

        List<AlunoEntity> lista = alunoRepository.findAll(
                spec,
                Sort.by(Sort.Order.asc("nome").ignoreCase())
        );

        return AlunoMapperUtil.toDomainList(lista, alunoEntityMapper);
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
    public boolean existsByEmailIgnoreCaseAndIdIsNot(String email, int id){
        return alunoRepository.existsByEmailIgnoreCaseAndIdIsNot(email, id);
    }

    @Override
    public boolean existsByCpfAndIdIsNot(String cpf, int id){
        return alunoRepository.existsByCpfAndIdIsNot(cpf, id);
    }

    @Override
    public boolean existsByRgAndIdIsNot(String rg, int id){
        return alunoRepository.existsByRgAndIdIsNot(rg, id);
    }

    @Override
    public List<Aluno> listarAniversariantes() {
        int mesAtual = LocalDate.now().getMonthValue();
        return AlunoMapperUtil.toDomainList(alunoRepository.findAniversariantes(mesAtual), alunoEntityMapper);
    }

    @Override
    public int qtdAlunosAtivos(){
        return (int) alunoRepository.countByAtivo(true);
    }

    @Override
    public Optional<Aluno> buscarPorEmailOuEmailResponsavel(String email) {
        return alunoRepository.findByEmailOrEmailResponsavel(email)
                .map(AlunoEntityMapper::toDomain);
    }
}
