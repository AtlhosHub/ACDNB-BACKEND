package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.usuario.*;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioRequestDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioResponseDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioListaDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioDTOMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "UsuarioController", description = "Endpoints para gerenciar usuários no sistema")
public class UsuarioController {
    private final AdicionarUsuarioUseCase adicionarUsuarioUseCase;
    private final ListarUsuariosUseCase listarUsuariosUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final RemoverUsuarioUseCase removerUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    public UsuarioController(AdicionarUsuarioUseCase adicionarUsuarioUseCase, ListarUsuariosUseCase listarUsuariosUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase, RemoverUsuarioUseCase removerUsuarioUseCase, AtualizarUsuarioUseCase atualizarUsuarioUseCase) {
        this.adicionarUsuarioUseCase = adicionarUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.removerUsuarioUseCase = removerUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> adicionarUsuario(@RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO executar = adicionarUsuarioUseCase.execute(usuario);
        return ResponseEntity.ok(executar);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListaDTO>> listarUsuarios() {
        List<UsuarioListaDTO> usuarios = listarUsuariosUseCase.execute()
                .stream()
                .map(UsuarioDTOMapper::toListaDTO)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable int id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(id);
        return usuario == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(UsuarioDTOMapper.toInfoDTO(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
        removerUsuarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable int id,
            @RequestBody UsuarioRequestDTO usuarioRequestDTO
    ) {
        UsuarioResponseDTO usuarioAtualizado = atualizarUsuarioUseCase.execute(id, usuarioRequestDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }
}
