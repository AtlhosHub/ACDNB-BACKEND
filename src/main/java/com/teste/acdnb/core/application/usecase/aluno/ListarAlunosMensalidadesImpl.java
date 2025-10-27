package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListarAlunosMensalidadesImpl implements ListarAlunosMensalidades{
    public final AlunoGateway alunoGateway;
    public final MensalidadeGateway mensalidadeGateway;

    public ListarAlunosMensalidadesImpl(AlunoGateway alunoGateway, MensalidadeGateway mensalidadeGateway) {
        this.alunoGateway = alunoGateway;
        this.mensalidadeGateway = mensalidadeGateway;
    }

    @Override
    public List<AlunoComprovanteDTO> execute(ListarAlunosMensalidadeFilter filter) {
        List<Aluno> alunos = alunoGateway.listarAlunosFiltro(filter);
        List<Mensalidade> mensalidades = mensalidadeGateway.listarMensalidadesFiltro(filter);

        List<AlunoComprovanteDTO> listaAlunos = alunos.stream()
                .map(aluno -> {
                    Mensalidade mensalidade = mensalidades.stream()
                            .filter(m -> m.getAluno().getId() == aluno.getId())
                            .findFirst()
                            .orElse(null);

                    if (mensalidade == null) return null;

                    return new AlunoComprovanteDTO(
                            mensalidade.getId(),
                            aluno.getId(),
                            aluno.getNomeSocial() == null ? aluno.getNome().getValue() : aluno.getNomeSocialValue(),
                            aluno.isAtivo(),
                            mensalidade.getDataPagamento(),
                            mensalidade.getDataVencimento(),
                            mensalidade.getStatusPagamento().name(),
                            mensalidade.getFormaPagamento() != null ? mensalidade.getFormaPagamento().name() : null,
                            mensalidade.getStatusPagamento() == StatusPagamento.PAGO ? mensalidade.getValor().getValor() : null,
                            mensalidade.getValor().isDesconto(),
                            mensalidade.isAlteracaoAutomatica()
                    );
                })
                .filter(Objects::nonNull)
                .skip(filter.offset())
                .limit(filter.limit())
                .collect(Collectors.toList());

        return listaAlunos;
    }
}
