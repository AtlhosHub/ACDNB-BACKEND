package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.usuario.*;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.*;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioDTOMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final BuscarUsuariosPorFiltroUseCase buscarUsuarioPorFiltroUseCase;
    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    public UsuarioController(AdicionarUsuarioUseCase adicionarUsuarioUseCase, ListarUsuariosUseCase listarUsuariosUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase, RemoverUsuarioUseCase removerUsuarioUseCase, AtualizarUsuarioUseCase atualizarUsuarioUseCase, BuscarUsuariosPorFiltroUseCase buscarUsuarioPorFiltroUseCase, AutenticarUsuarioUseCase autenticarUsuarioUseCase) {
        this.adicionarUsuarioUseCase = adicionarUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.removerUsuarioUseCase = removerUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.buscarUsuarioPorFiltroUseCase = buscarUsuarioPorFiltroUseCase;
        this.autenticarUsuarioUseCase = autenticarUsuarioUseCase;
    }

    // @SecurityRequirement(name = "Bearer")
    @PostMapping("/adicionar")
    public ResponseEntity<UsuarioResponseDTO> adicionarUsuario(@RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO executar = adicionarUsuarioUseCase.execute(usuario);
        return ResponseEntity.ok(executar);
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<UsuarioListaDTO>> listarUsuarios() {
        List<UsuarioListaDTO> usuarios = listarUsuariosUseCase.execute()
                .stream()
                .map(UsuarioDTOMapper::toListaDTO)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable int id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(id);
        return usuario == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(UsuarioDTOMapper.toInfoDTO(usuario));
    }

    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
        removerUsuarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable int id,
            @RequestBody UsuarioRequestDTO usuarioRequestDTO
    ) {
        UsuarioResponseDTO usuarioAtualizado = atualizarUsuarioUseCase.execute(id, usuarioRequestDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @SecurityRequirement(name = "Bearer")
    @PostMapping("/filtro")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuariosPorNome(
            @RequestBody UsuarioFiltroDTO usuarioFiltroDTO) {

        List<UsuarioResponseDTO> usuarios = buscarUsuarioPorFiltroUseCase.execute(usuarioFiltroDTO);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDTO> login(
            @RequestBody UsuarioLoginDTO usuarioLoginDTO){
        UsuarioTokenDTO usuarioTokenDTO = autenticarUsuarioUseCase.execute(usuarioLoginDTO);
        return ResponseEntity.ok(usuarioTokenDTO);
    }
}
