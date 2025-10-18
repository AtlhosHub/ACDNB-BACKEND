package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.mensalidade.AtualizarMensalidade;
import com.teste.acdnb.core.application.usecase.mensalidade.ContarMensalidadeComDesconto;
import com.teste.acdnb.core.application.usecase.mensalidade.GerarRelatorioMensalidadePorMes;
import com.teste.acdnb.core.application.usecase.mensalidade.dto.RelatorioMensalidade;
import com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade.AdicionarValorMensalidade;
import com.teste.acdnb.core.application.usecase.mensalidade.entities.valorMensalidade.BuscarValorMensalidadeAtual;
import com.teste.acdnb.core.domain.mensalidade.Mensalidade;
import com.teste.acdnb.core.domain.mensalidade.entities.ValorMensalidade.ValorMensalidade;
import com.teste.acdnb.infrastructure.dto.PagamentoManualDTO;
import com.teste.acdnb.infrastructure.dto.mensaldiade.NovoValorMensalidadeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensalidades")
@SecurityRequirement(name = "Bearer")
@Tag(name = "MensalidadeController", description = "Endpoints para gerenciar os as mensalidades no sistema")
public class MensalidadeController {
    private final GerarRelatorioMensalidadePorMes gerarRelatorioMensalidadePorMes;
    private final ContarMensalidadeComDesconto contarMensalidadeComDesconto;
    private final AtualizarMensalidade atualizarMensalidade;
    private final BuscarValorMensalidadeAtual buscarValorMensalidadeAtual;
    private final AdicionarValorMensalidade adicionarValorMensalidade;

    public MensalidadeController(
            GerarRelatorioMensalidadePorMes gerarRelatorioMensalidadePorMes,
            ContarMensalidadeComDesconto contarMensalidadeComDesconto,
            AtualizarMensalidade atualizarMensalidade,
            BuscarValorMensalidadeAtual buscarValorMensalidadeAtual,
            AdicionarValorMensalidade adicionarValorMensalidade

    ) {
        this.gerarRelatorioMensalidadePorMes = gerarRelatorioMensalidadePorMes;
        this.contarMensalidadeComDesconto = contarMensalidadeComDesconto;
        this.atualizarMensalidade = atualizarMensalidade;
        this.buscarValorMensalidadeAtual = buscarValorMensalidadeAtual;
        this.adicionarValorMensalidade = adicionarValorMensalidade;
    }

    @GetMapping("/grafico")
    @Operation(summary = "Gerar gráfico de mensalidades", description = "Retorna os dados para gerar um gráfico de mensalidades pagas e pendentes.")
    public ResponseEntity<List<RelatorioMensalidade>> gerarRelatorioMensalidadePorMes() {
        List<RelatorioMensalidade> relatorioMensalidade = gerarRelatorioMensalidadePorMes.execute();
        return ResponseEntity.ok(relatorioMensalidade);
    }

    @GetMapping("/qtd-descontos")
    @Operation(summary = "Contar mensalidades com desconto", description = "Retorna a quantidade de mensalidades pagas com desconto no mês atual.")
    public ResponseEntity<Integer> countMensalidadesDesconto() {
        Integer qtdMensalidades = contarMensalidadeComDesconto.execute();
        return ResponseEntity.ok(qtdMensalidades);
    }

    @PutMapping("/{id}/pagar")
    @Operation(summary = "Registrar pagamento manualmente", description = "Registra o pagamento realizado por um aluno.")
    public ResponseEntity<Mensalidade> pagarManual(
            @PathVariable Long id,
            @RequestBody @Valid PagamentoManualDTO dto
    ) {
        Mensalidade mensalidadeAtualizada = atualizarMensalidade.execute(id, dto);
        return ResponseEntity.ok(mensalidadeAtualizada);
    }

    @GetMapping("/valor-atual")
    @Operation(summary = "Puxa o valor de mensalidade atual", description = "Puxa qual valor de mensalidade está sendo usado atualmente.")
    public ResponseEntity<ValorMensalidade> valorMensalidadeAtual() {
        ValorMensalidade valorMensalidadeAtual = buscarValorMensalidadeAtual.execute();
        return ResponseEntity.ok(valorMensalidadeAtual);
    }

    @PostMapping("/valor-mensalidade")
    @Operation(summary = "Adiciona novo valor de mensalidade", description = "Adiciona um novo valor de mensalidade")
    public ResponseEntity<ValorMensalidade> adicionarValorMensalidadee(@RequestBody NovoValorMensalidadeDTO novoValor) {
        ValorMensalidade novoValorMensalidade = adicionarValorMensalidade.execute(novoValor);

        return ResponseEntity.ok(novoValorMensalidade);
    }
}