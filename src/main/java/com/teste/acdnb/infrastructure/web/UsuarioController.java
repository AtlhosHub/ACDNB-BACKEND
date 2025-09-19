package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.usuario.*;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.usuario.*;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Adicionar um novo usuário", description = "Adiciona um novo usuário ao sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "Erro(s) de validação: nome: O nome do usuário não pode ficar em branco"))),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content()),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrados", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content())
    })
    public ResponseEntity<UsuarioResponseDTO> adicionarUsuario(@RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO executar = adicionarUsuarioUseCase.execute(usuario);
        return ResponseEntity.ok(executar);
    }
    
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Listar usuários", description = "Retorna uma lista de todos os usuários cadastrados no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content())
    })
    public ResponseEntity<List<UsuarioListaDTO>> listarUsuarios() {
        List<UsuarioListaDTO> usuarios = listarUsuariosUseCase.execute()
                .stream()
                .map(UsuarioDTOMapper::toListaDTO)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os detalhes de um usuário específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dados do usuário retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content())
    })
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable int id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.execute(id);
        return usuario == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(UsuarioDTOMapper.toInfoDTO(usuario));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Remover usuário por ID", description = "Remove um usuário do sistema com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso", content = @Content()),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content())
    })
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
        removerUsuarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Atualizar usuário por ID", description = "Atualiza os dados de um usuário existente com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "Erro(s) de validação: nome: O nome do usuário não pode ficar em branco"))),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content()),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrados", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content())
    })
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable int id,
            @RequestBody UsuarioRequestDTO usuarioRequestDTO
    ) {
        UsuarioResponseDTO usuarioAtualizado = atualizarUsuarioUseCase.execute(id, usuarioRequestDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PostMapping("/filtro")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Filtrar usuários", description = "Retorna uma lista de usuários com base nos critérios do filtro fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content())
    })
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuariosPorNome(
            @RequestBody UsuarioFiltroDTO usuarioFiltroDTO) {

        List<UsuarioResponseDTO> usuarios = buscarUsuarioPorFiltroUseCase.execute(usuarioFiltroDTO);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Realiza a autenticação de um usuário e retorna um token de acesso.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content())
    })
    public ResponseEntity<UsuarioTokenDTO> login(
            @RequestBody UsuarioLoginDTO usuarioLoginDTO){
        UsuarioTokenDTO usuarioTokenDTO = autenticarUsuarioUseCase.execute(usuarioLoginDTO);
        return ResponseEntity.ok(usuarioTokenDTO);
    }

    @GetMapping("/token-validation")
    public ResponseEntity<Void> tokenValidation() {
        return ResponseEntity.noContent().build();
    }
}
