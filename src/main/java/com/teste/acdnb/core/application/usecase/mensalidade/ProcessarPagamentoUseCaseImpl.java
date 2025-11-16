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
import com.teste.acdnb.core.domain.shared.valueobject.Nome;
import com.teste.acdnb.infrastructure.dto.mensaldiade.ComprovanteDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProcessarPagamentoUseCaseImpl implements ProcessarPagamentoUseCase{

    private final AlunoGateway alunoGateway;
    private final MensalidadeGateway mensalidadeGateway;
    private final ComprovanteGateway comprovanteGateway;

    private static final BigDecimal DESCONTO_ANTECIPADO = new BigDecimal("10.00");

    public ProcessarPagamentoUseCaseImpl(AlunoGateway alunoGateway,
                                         MensalidadeGateway mensalidadeGateway,
                                         ComprovanteGateway comprovanteGateway) {
        this.alunoGateway = alunoGateway;
        this.mensalidadeGateway = mensalidadeGateway;
        this.comprovanteGateway = comprovanteGateway;
    }

    @Override
    public void execute(ComprovanteDTO comprovanteDTO) {
        try {
            System.out.println("🔄 Iniciando processamento de pagamento para: " + comprovanteDTO.emailDestinatario());

            Aluno aluno = buscarAlunoPorEmail(comprovanteDTO.emailDestinatario());
            System.out.println("✅ Aluno encontrado: " + aluno.getNome());

            Comprovante comprovante = converterParaComprovante(comprovanteDTO, aluno);

            Comprovante comprovanteSalvo = comprovanteGateway.salvar(comprovante);
            System.out.println("✅ Comprovante salvo com ID: " + comprovanteSalvo.getId());

            processarMensalidades(aluno, comprovanteSalvo);

            System.out.println("🎉 Pagamento processado com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao processar pagamento: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Falha ao processar pagamento", e);
        }
    }

    private Aluno buscarAlunoPorEmail(String emailDestinatario) {
        System.out.println("🔍 Buscando aluno pelo email: " + emailDestinatario);
        return alunoGateway.buscarPorEmailOuEmailResponsavel(emailDestinatario)
                .orElseThrow(() -> new RuntimeException(
                        "Aluno não encontrado para o email: " + emailDestinatario
                ));
    }

    private Comprovante converterParaComprovante(ComprovanteDTO dto, Aluno aluno) {
        Comprovante comprovante = new Comprovante();

        comprovante.setNomeRemetente(Nome.of(dto.nomeRemetente()));
        comprovante.setDataEnvio(dto.dataHora());
        comprovante.setBancoOrigem(dto.bancoOrigem());
        comprovante.setBancoDestino(dto.bancoDestino());

        ValoresComprovante valores = new ValoresComprovante();
        valores.setValorCheio(new BigDecimal(dto.valor()));
        comprovante.setValores(valores);

        comprovante.setAluno(aluno);

        return comprovante;
    }

    private void processarMensalidades(Aluno aluno, Comprovante comprovante) {
        List<Mensalidade> mensalidadesPendentes = mensalidadeGateway
                .buscarMensalidadesPendentesOuAtrasadasPorAluno(aluno);

        if (mensalidadesPendentes.isEmpty()) {
            System.out.println("⚠️ Nenhuma mensalidade pendente encontrada para o aluno");
            return;
        }

        System.out.println("📋 Encontradas " + mensalidadesPendentes.size() + " mensalidades pendentes");

        List<Mensalidade> mensalidadesOrdenadas = new ArrayList<>(mensalidadesPendentes);
        mensalidadesOrdenadas.sort(Comparator.comparing(Mensalidade::getDataVencimento));

        processarPagamentoComDesconto(mensalidadesOrdenadas, comprovante);
    }

    private void processarPagamentoComDesconto(List<Mensalidade> mensalidadesOrdenadas, Comprovante comprovante) {
        BigDecimal valorRecebido = comprovante.getValores().getValorCheio();
        BigDecimal valorTotalPendente = calcularValorTotalPendente(mensalidadesOrdenadas);

        System.out.println("💰 Valor recebido: " + valorRecebido);
        System.out.println("📊 Valor total pendente: " + valorTotalPendente);

        BigDecimal valorTotalComDesconto = calcularValorTotalComDesconto(mensalidadesOrdenadas, comprovante.getDataEnvio().toLocalDate());
        System.out.println("🎯 Valor total com descontos aplicáveis: " + valorTotalComDesconto);

        if (!isValorSuficienteParaUmaMensalidadeComDesconto(valorRecebido, mensalidadesOrdenadas, comprovante.getDataEnvio().toLocalDate())) {
            throw new RuntimeException("Valor recebido R$ " + valorRecebido + " é insuficiente para pagar qualquer mensalidade, mesmo com descontos");
        }

        List<Mensalidade> mensalidadesProcessadas = aplicarPagamentoComDescontoProporcional(mensalidadesOrdenadas, comprovante, valorRecebido);

        mensalidadeGateway.salvarTodas(mensalidadesProcessadas);
        System.out.println("✅ " + mensalidadesProcessadas.size() + " mensalidades processadas com sucesso!");
    }

    private BigDecimal calcularValorTotalPendente(List<Mensalidade> mensalidades) {
        return mensalidades.stream()
                .map(mensalidade -> mensalidade.getValor().getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularValorTotalComDesconto(List<Mensalidade> mensalidades, LocalDate dataPagamento) {
        return mensalidades.stream()
                .map(mensalidade -> calcularValorComDesconto(mensalidade, dataPagamento))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean isValorSuficienteParaUmaMensalidadeComDesconto(BigDecimal valorRecebido, List<Mensalidade> mensalidades, LocalDate dataPagamento) {
        if (mensalidades.isEmpty()) return false;

        BigDecimal menorMensalidadeComDesconto = mensalidades.stream()
                .map(mensalidade -> calcularValorComDesconto(mensalidade, dataPagamento))
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        boolean isSuficiente = valorRecebido.compareTo(menorMensalidadeComDesconto) >= 0;

        if (!isSuficiente) {
            System.out.println("❌ Valor recebido R$ " + valorRecebido + " é menor que a menor mensalidade com desconto: R$ " + menorMensalidadeComDesconto);
        }

        return isSuficiente;
    }

    private List<Mensalidade> aplicarPagamentoComDescontoProporcional(List<Mensalidade> mensalidadesOrdenadas,
                                                                      Comprovante comprovante,
                                                                      BigDecimal valorRecebido) {
        List<Mensalidade> mensalidadesProcessadas = new ArrayList<>();
        BigDecimal valorRestante = valorRecebido;
        LocalDate dataPagamento = comprovante.getDataEnvio().toLocalDate();

        for (Mensalidade mensalidade : mensalidadesOrdenadas) {
            if (valorRestante.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }

            BigDecimal valorMensalidadeComDesconto = calcularValorComDesconto(mensalidade, dataPagamento);

            if (valorRestante.compareTo(valorMensalidadeComDesconto) >= 0) {
                boolean teveDesconto = aplicarPagamentoMensalidadeComDesconto(mensalidade, comprovante, valorMensalidadeComDesconto);
                mensalidadesProcessadas.add(mensalidade);
                valorRestante = valorRestante.subtract(valorMensalidadeComDesconto);

                String descontoInfo = teveDesconto ? " (com desconto de R$ 10,00)" : "";
                System.out.println("✅ Mensalidade " + mensalidade.getId() + " paga por R$ " + valorMensalidadeComDesconto + descontoInfo + ". Valor restante: R$ " + valorRestante);
            } else {
                System.out.println("⏭️  Valor insuficiente para mensalidade " + mensalidade.getId() +
                        ". Valor necessário: R$ " + valorMensalidadeComDesconto +
                        ", Valor restante: R$ " + valorRestante);
            }
        }

        if (valorRestante.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("💡 Valor excedente de R$ " + valorRestante + " será ignorado");
        }

        return mensalidadesProcessadas;
    }

    /**
        Calcula o valor da mensalidade com desconto se aplicável
    */
    private BigDecimal calcularValorComDesconto(Mensalidade mensalidade, LocalDate dataPagamento) {
        BigDecimal valorOriginal = mensalidade.getValor().getValor();

        if (deveAplicarDesconto(mensalidade, dataPagamento)) {
            BigDecimal valorComDesconto = valorOriginal.subtract(DESCONTO_ANTECIPADO);
            return valorComDesconto.compareTo(BigDecimal.ZERO) > 0 ? valorComDesconto : BigDecimal.ZERO;
        }

        return valorOriginal;
    }

    /**
     * Verifica se a mensalidade deve receber desconto (pagamento antecipado)
     */
    private boolean deveAplicarDesconto(Mensalidade mensalidade, LocalDate dataPagamento) {
        return dataPagamento.isBefore(mensalidade.getDataVencimento());
    }

    /**
     * Aplica o pagamento na mensalidade considerando possível desconto
     * @return true se foi aplicado desconto, false caso contrário
    */
    private boolean aplicarPagamentoMensalidadeComDesconto(Mensalidade mensalidade, Comprovante comprovante, BigDecimal valorPago) {
        boolean teveDesconto = deveAplicarDesconto(mensalidade, comprovante.getDataEnvio().toLocalDate());

        mensalidade.setStatusPagamento(StatusPagamento.PAGO);
        mensalidade.setFormaPagamento(FormaPagamento.PIX);
        mensalidade.setDataPagamento(comprovante.getDataEnvio());
        mensalidade.setAlteracaoAutomatica(true);
        mensalidade.setComprovante(comprovante);

        if (teveDesconto) {
            System.out.println("🎁 Desconto de R$ 10,00 aplicado na mensalidade " + mensalidade.getId() +
                    " - Valor original: R$ " + mensalidade.getValor().getValor() +
                    ", Valor pago: R$ " + valorPago);
        }

        BigDecimal valorOriginal = mensalidade.getValor().getValor();
        System.out.println("💰 Mensalidade " + mensalidade.getId() +
                " - Valor Original: R$ " + valorOriginal +
                " - Valor Pago: R$ " + valorPago +
                " - Data Pagamento: " + mensalidade.getDataPagamento() +
                " - Data Vencimento: " + mensalidade.getDataVencimento() +
                " - Desconto: " + (teveDesconto ? "R$ 10,00" : "Nenhum") +
                " - Forma: " + mensalidade.getFormaPagamento());

        return teveDesconto;
    }
}