package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.filter.AlunoFilter;

import java.util.ArrayList;
import java.util.List;

public class ListarAlunosMensalidadesImpl implements ListarAlunosMensalidades{
    public final AlunoGateway alunoGateway;
    public final MensalidadeGateway mensalidadeGateway;

    public ListarAlunosMensalidadesImpl(AlunoGateway alunoGateway, MensalidadeGateway mensalidadeGateway) {
        this.alunoGateway = alunoGateway;
        this.mensalidadeGateway = mensalidadeGateway;
    }

    @Override
    public List<AlunoComprovanteDTO> execute(AlunoFilter filter) {
        List<AlunoComprovanteDTO> listaAlunos = new ArrayList<>();
        List<Aluno> alunos = alunoGateway.listarAlunosFiltro(filter);

        for (Aluno aluno : alunos) {
            List<Mensalidade> mensalidades = mensalidadeGateway.listarMensalidadesFiltro(filter);
            for (Mensalidade mensalidade : mensalidades) {
                listaAlunos.add(new AlunoComprovanteDTO(
                        mensalidade.getId(),
                        aluno.getId(),
                        aluno.getNomeSocial() == null ? aluno.getNome().getValue() : aluno.getNomeSocial().getValue(),
                        aluno.isAtivo(),
                        mensalidade.getDataPagamento(),
                        mensalidade.getDataVencimento(),
                        mensalidade.getStatusPagamento().name(),
                        mensalidade.getFormaPagamento() != null ? mensalidade.getFormaPagamento().name() : null,
                        mensalidade.getStatusPagamento() == StatusPagamento.PAGO ? mensalidade.getValor().getValor() : null,
                        mensalidade.getValor().isDesconto(),
                        mensalidade.isAlteracaoAutomatica()
                ));
            }
        }

        return listaAlunos;
    }
}
