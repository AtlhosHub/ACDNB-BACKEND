package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.infrastructure.dto.mensalidade.RelatorioMensalidadeDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensalidades")
@SecurityRequirement(name = "Bearer")
@Tag(name = "MensalidadeController", description = "Endpoints para gerenciar os as mensalidades no sistema")
public class MensalidadeController {
    private final MensalidadeRepository mensalidadeRepository;

    public MensalidadeController(MensalidadeRepository mensalidadeRepository) {
        this.mensalidadeRepository = mensalidadeRepository;
    }

    @GetMapping("/grafico")
    @Operation(summary = "Gerar gráfico de mensalidades", description = "Retorna os dados para gerar um gráfico de mensalidades pagas e pendentes.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados do gráfico retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content())
    })
    public ResponseEntity<List<RelatorioMensalidadeDTO>> relatorioMensalidadePorMes() {
        return ResponseEntity.ok(mensalidadeRepository.relatorioMensalidadePorMes());
    }
}