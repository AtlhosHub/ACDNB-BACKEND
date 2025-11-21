package com.teste.acdnb.core.application.usecase.mensalidade;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.ComprovanteGateway;
import com.teste.acdnb.core.application.gateway.mensalidade.MensalidadeGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.entities.Comprovante;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.valueobject.ValoresComprovante;
import com.teste.acdnb.core.domain.mensalidade.enums.FormaPagamento;
import com.teste.acdnb.core.domain.mensalidade.enums.StatusPagamento;
import com.teste.acdnb.core.domain.mensalidade.enums.TipoRetornoPagamento;
import com.teste.acdnb.core.domain.shared.valueobject.Nome;
import com.teste.acdnb.infrastructure.dto.RespostaPagementoDTO;
import com.teste.acdnb.infrastructure.dto.mensaldiade.ComprovanteDTO;
import com.teste.acdnb.infrastructure.security.PagamentoRetornoProdutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class ProcessarPagamentoUseCaseImpl implements ProcessarPagamentoUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessarPagamentoUseCaseImpl.class);

    private final AlunoGateway alunoGateway;
    private final MensalidadeGateway mensalidadeGateway;
    private final ComprovanteGateway comprovanteGateway;
    private final PagamentoRetornoProdutor produtorMensagem;

    private static final BigDecimal DESCONTO_ANTECIPADO = new BigDecimal("10.00");

    public ProcessarPagamentoUseCaseImpl(
            AlunoGateway alunoGateway,
            MensalidadeGateway mensalidadeGateway,
            ComprovanteGateway comprovanteGateway,
            PagamentoRetornoProdutor produtorMensagem
    ) {
        this.alunoGateway = alunoGateway;
        this.mensalidadeGateway = mensalidadeGateway;
        this.comprovanteGateway = comprovanteGateway;
        this.produtorMensagem = produtorMensagem;
    }

    // ============================================================
    //   EXCEÇÕES ESPECÍFICAS
    // ============================================================

    private static class AlunoNaoEncontradoException extends RuntimeException {
        public AlunoNaoEncontradoException(String msg) { super(msg); }
    }

    private static class NenhumaMensalidadeException extends RuntimeException {
        public NenhumaMensalidadeException(String msg) { super(msg); }
    }

    private static class PagamentoInsuficienteException extends RuntimeException {
        public PagamentoInsuficienteException(String msg) { super(msg); }
    }

    // ============================================================
    //   MÉTODO PRINCIPAL - CORRIGIDO
    // ============================================================

    @Override
    public void execute(ComprovanteDTO dto) {
        log.info("📨 Processando comprovante para email: {}", dto.emailDestinatario());

        try {
            validarComprovanteDTO(dto);

            Aluno aluno = buscarAluno(dto.emailDestinatario());
            Comprovante comprovante = salvarComprovante(dto, aluno);
            List<Mensalidade> mensalidades = buscarMensalidadesPendentes(aluno);

            aplicarPagamento(aluno, mensalidades, comprovante);

        } catch (AlunoNaoEncontradoException e) {
            log.warn("Aluno não encontrado: {}", e.getMessage());
            enviarRespostaErro(dto.emailDestinatario(), TipoRetornoPagamento.ALUNO_NAO_ENCONTRADO, e.getMessage());

        } catch (NenhumaMensalidadeException e) {
            log.warn("Nenhuma mensalidade encontrada: {}", e.getMessage());
            enviarRespostaErro(dto.emailDestinatario(), TipoRetornoPagamento.NENHUMA_MENSALIDADE_ENCONTRADA, e.getMessage());

        } catch (PagamentoInsuficienteException e) {
            log.warn("Pagamento insuficiente: {}", e.getMessage());
            enviarRespostaErro(dto.emailDestinatario(), TipoRetornoPagamento.VALOR_INSUFICIENTE, e.getMessage());

        } catch (Exception e) {
            log.error("Erro desconhecido ao processar pagamento para: {}", dto.emailDestinatario(), e);
            String mensagemErro = e.getMessage() != null ? e.getMessage() : "Erro interno no processamento";
            enviarRespostaErro(dto.emailDestinatario(), TipoRetornoPagamento.ERRO_DESCONHECIDO, mensagemErro);
        }
    }

    // ============================================================
    //   MÉTODOS AUXILIARES CORRIGIDOS
    // ============================================================

    private void validarComprovanteDTO(ComprovanteDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ComprovanteDTO não pode ser nulo");
        }
        if (dto.emailDestinatario() == null || dto.emailDestinatario().trim().isEmpty()) {
            throw new IllegalArgumentException("Email destinatário é obrigatório");
        }
        if (dto.valor() == null || dto.valor().trim().isEmpty()) {
            throw new IllegalArgumentException("Valor é obrigatório");
        }
    }

    private void enviarRespostaErro(String email, TipoRetornoPagamento tipo, String mensagem) {
        produtorMensagem.enviarMensagem(
                new RespostaPagementoDTO(
                        email,
                        tipo,
                        mensagem,
                        null, null, null,
                        List.of(), List.of()
                )
        );
    }

    private Aluno buscarAluno(String email) {
        log.debug("Buscando aluno por email: {}", email);
        return alunoGateway.buscarPorEmailOuEmailResponsavel(email)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado para o e-mail: " + email));
    }

    private Comprovante salvarComprovante(ComprovanteDTO dto, Aluno aluno) {
        try {
            log.debug("Salvando comprovante para aluno: {}", aluno.getId());

            Comprovante comprovante = new Comprovante();
            comprovante.setNomeRemetente(Nome.of(dto.nomeRemetente()));
            comprovante.setBancoOrigem(dto.bancoOrigem());
            comprovante.setBancoDestino(dto.bancoDestino());
            comprovante.setDataEnvio(dto.dataHora());

            ValoresComprovante valores = new ValoresComprovante();

            // Conversão segura do valor
            BigDecimal valor = converterStringParaBigDecimal(dto.valor());
            valores.setValorCheio(valor);
            comprovante.setValores(valores);

            return comprovanteGateway.salvar(comprovante);

        } catch (Exception e) {
            log.error("Erro ao salvar comprovante", e);
            throw new RuntimeException("Erro ao processar comprovante: " + e.getMessage(), e);
        }
    }

    private BigDecimal converterStringParaBigDecimal(String valorStr) {
        try {
            String valorLimpo = valorStr.replace("R$", "").replace(" ", "").trim();

            valorLimpo = valorLimpo.replace(",", ".");

            return new BigDecimal(valorLimpo);

        } catch (NumberFormatException e) {
            log.error("Erro ao converter valor: {}", valorStr);
            throw new IllegalArgumentException("Valor inválido: " + valorStr);
        }
    }

    private List<Mensalidade> buscarMensalidadesPendentes(Aluno aluno) {
        log.debug("Buscando mensalidades pendentes para aluno: {}", aluno.getId());
        List<Mensalidade> mensalidades = mensalidadeGateway.buscarMensalidadesPendentesOuAtrasadasPorAluno(aluno);

        if (mensalidades.isEmpty()) {
            throw new NenhumaMensalidadeException("Nenhuma mensalidade pendente ou atrasada encontrada para o aluno");
        }

        List<Mensalidade> mensalidadesOrdenadas = new ArrayList<>(mensalidades);
        mensalidadesOrdenadas.sort(Comparator.comparing(Mensalidade::getDataVencimento));

        log.debug("Encontradas {} mensalidades pendentes", mensalidadesOrdenadas.size());
        return mensalidadesOrdenadas;
    }

    // ============================================================
    //   PROCESSAMENTO DO PAGAMENTO (mantido igual)
    // ============================================================

    private void aplicarPagamento(Aluno aluno, List<Mensalidade> mensalidades, Comprovante comprovante) {
        BigDecimal valorRecebido = comprovante.getValores().getValorCheio();
        LocalDate dataPagamento = comprovante.getDataEnvio().toLocalDate();

        log.info("Processando pagamento de R$ {} para {} mensalidades", valorRecebido, mensalidades.size());

        BigDecimal menorMensalidadeComDesconto = mensalidades.stream()
                .map(m -> calcularComDesconto(m, dataPagamento))
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        if (valorRecebido.compareTo(menorMensalidadeComDesconto) < 0) {
            throw new PagamentoInsuficienteException(
                    "Valor recebido R$ " + valorRecebido +
                            " é insuficiente para pagar a menor mensalidade (R$ " +
                            menorMensalidadeComDesconto + ")."
            );
        }

        List<Mensalidade> pagas = new ArrayList<>();
        BigDecimal restante = valorRecebido;

        for (Mensalidade m : mensalidades) {
            BigDecimal valorComDesconto = calcularComDesconto(m, dataPagamento);

            if (restante.compareTo(valorComDesconto) >= 0) {
                aplicarPagamentoNaMensalidade(m, comprovante, valorComDesconto);
                pagas.add(m);
                restante = restante.subtract(valorComDesconto);
            } else {
                break;
            }
        }

        mensalidadeGateway.salvarTodas(pagas);

        log.info("Pagamento processado: {} mensalidades pagas, restante: R$ {}", pagas.size(), restante);

        produtorMensagem.enviarMensagem(
                new RespostaPagementoDTO(
                        aluno.getEmail().getValue(),
                        pagas.size() == mensalidades.size()
                                ? TipoRetornoPagamento.PAGAMENTO_PROCESSADO
                                : TipoRetornoPagamento.PAGAMENTO_PARCIAL,
                        pagas.size() == mensalidades.size()
                                ? "Pagamento realizado com sucesso!"
                                : "Pagamento parcial realizado.",
                        valorRecebido,
                        restante,
                        BigDecimal.ZERO,
                        pagas.stream().map(m -> Long.valueOf(m.getId())).toList(),
                        List.of()
                )
        );
    }

    private BigDecimal calcularComDesconto(Mensalidade m, LocalDate dataPagamento) {
        BigDecimal valorOriginal = m.getValor().getValor();

        if (dataPagamento.isBefore(m.getDataVencimento())) {
            BigDecimal comDesconto = valorOriginal.subtract(DESCONTO_ANTECIPADO);
            return comDesconto.max(BigDecimal.ZERO);
        }

        return valorOriginal;
    }

    private void aplicarPagamentoNaMensalidade(Mensalidade mensalidade, Comprovante comprovante, BigDecimal valorPago) {
        mensalidade.setStatusPagamento(StatusPagamento.PAGO);
        mensalidade.setFormaPagamento(FormaPagamento.PIX);
        mensalidade.setAlteracaoAutomatica(true);
        mensalidade.setDataPagamento(comprovante.getDataEnvio());
        mensalidade.setComprovante(comprovante);
    }
}