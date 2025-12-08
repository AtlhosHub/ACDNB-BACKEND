package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.usuario.RecuperarSenhaUseCase;
import com.teste.acdnb.infrastructure.dto.usuario.RecuperarSenhaRequestDTO;
import com.teste.acdnb.infrastructure.dto.usuario.ResetSenhaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resetPassword")
@Tag(name = "RecuperacaoSenhaController", description = "Endpoints para recuperação de senha")
public class RecuperacaoSenhaController {

    private final RecuperarSenhaUseCase recuperarSenhaUseCase;

    public RecuperacaoSenhaController(RecuperarSenhaUseCase recuperarSenhaUseCase) {
        this.recuperarSenhaUseCase = recuperarSenhaUseCase;
    }

    @PostMapping("/request-reset")
    @Operation(summary = "Solicitar recuperação de senha",
            description = "Envia um email com link para recuperação de senha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Email enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> solicitarRecuperacaoSenha(@RequestBody RecuperarSenhaRequestDTO request) {
        try {
            recuperarSenhaUseCase.solicitarRecuperacaoSenha(request.getEmail());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("não encontrado")) {
                // Retorna 404 para email não encontrado (mantendo a API documentada)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            throw e;
        }
    }

    @GetMapping("/validate")
    @Operation(summary = "Validar token de recuperação",
            description = "Valida se o token de recuperação de senha é válido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token válido"),
            @ApiResponse(responseCode = "400", description = "Token inválido ou expirado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> validarToken(@RequestParam String token) {
        boolean valido = recuperarSenhaUseCase.validarToken(token);
        return valido ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Redefinir senha",
            description = "Redefine a senha do usuário usando o token de recuperação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Senha redefinida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Token inválido ou senha inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> resetarSenha(@RequestBody ResetSenhaDTO request) {
        recuperarSenhaUseCase.resetarSenha(request.getToken(), request.getNovaSenha());
        return ResponseEntity.ok().build();
    }
}