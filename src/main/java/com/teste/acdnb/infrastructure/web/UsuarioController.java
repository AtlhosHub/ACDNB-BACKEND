package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.usuario.AdicionarUsuarioUseCase;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.UsuarioDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "UsuarioController", description = "Endpoints para gerenciar usuários no sistema")
public class UsuarioController {
    private final AdicionarUsuarioUseCase adicionarUsuarioUseCase;

    public UsuarioController(AdicionarUsuarioUseCase adicionarUsuarioUseCase) {
        this.adicionarUsuarioUseCase = adicionarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody UsuarioDTO usuario) {
        Usuario executar = adicionarUsuarioUseCase.execute(usuario);
        return ResponseEntity.ok(executar);
    }
}
