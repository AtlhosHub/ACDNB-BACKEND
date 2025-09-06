package com.teste.acdnb.infrastructure.gateway.mensalidade;

import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.core.application.usecase.mensalidade.dto.RelatorioMensalidade;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MensalidadeRepositoryGateway implements MensalidadeGateway {
    private final MensalidadeRepository mensalidadeRepository;
    private final MensalidadeEntityMapper mensalidadeEntityMapper;

    public MensalidadeRepositoryGateway(MensalidadeRepository mensalidadeRepository, MensalidadeEntityMapper mensalidadeEntityMapper) {
        this.mensalidadeRepository = mensalidadeRepository;
        this.mensalidadeEntityMapper = mensalidadeEntityMapper;
    }

    @Override
    public void salvarTodas(List<Mensalidade> mensalidades) {
        for(int i = 0; i <= mensalidades.size(); i++) {
            MensalidadeEntity mensalidadeEntity = MensalidadeEntityMapper.toEntity(mensalidades.get(0));
            MensalidadeEntity novaMensalidade = mensalidadeRepository.save(mensalidadeEntity);
        }
    }

    @Override
    public Mensalidade salvar(Mensalidade mensalidade) {
        MensalidadeEntity mensalidadeEntity = mensalidadeEntityMapper.toEntity(mensalidade);
        MensalidadeEntity novaMensalidade = mensalidadeRepository.save(mensalidadeEntity);

        return mensalidadeEntityMapper.toDomain(novaMensalidade);
    }

    @Override
    public long contarMensalidadePendentesOuAtrasadas(Aluno aluno) {
        return mensalidadeRepository
                .countByAlunoAndStatusPagamentoIn(
                        aluno,
                        List.of(StatusPagamento.PENDENTE, StatusPagamento.ATRASADO)
                );
    }

    @Override
    public int contarMensalidadeComDesconto() {
        return mensalidadeRepository.countMensalidadeComDesconto();
    }

    @Override
    public List<RelatorioMensalidade> gerarRelatorioMensalidadePorMes() {
        return mensalidadeRepository.gerarRelatorioMensalidadePorMes()
                .stream()
                .map(dto -> new RelatorioMensalidade(
                        dto.mes(),
                        dto.atrasados(),
                        dto.pagos(),
                        dto.pagos_com_desconto()
                ))
                .toList();
    }

    @Override
    public Optional<Mensalidade> buscarMensalidadePorId(Long id){
        Optional<MensalidadeEntity> mensalidade = mensalidadeRepository.findById(Integer.valueOf(id.toString()));

        return mensalidade.map(MensalidadeEntityMapper::toDomain);
    };
}
