package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.usuario.AdicionarUsuarioUseCase;
import com.teste.acdnb.core.application.usecase.usuario.BuscarUsuarioPorIdUseCase;
import com.teste.acdnb.core.application.usecase.usuario.ListarUsuariosUseCase;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioInfoDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioListaDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioDTOMapper;
import io.swagger.v3.oas.annotations.Parameter;
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

    public UsuarioController(AdicionarUsuarioUseCase adicionarUsuarioUseCase, ListarUsuariosUseCase listarUsuariosUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase) {
        this.adicionarUsuarioUseCase = adicionarUsuarioUseCase;
        this.listarUsuariosUseCase = listarUsuariosUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody UsuarioDTO usuario) {
        Usuario executar = adicionarUsuarioUseCase.execute(usuario);
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
    public ResponseEntity<UsuarioInfoDTO> buscarUsuarioPorId(@PathVariable int id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(id);
        return usuario == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(UsuarioDTOMapper.toInfoDTO(usuario));
    }
}
