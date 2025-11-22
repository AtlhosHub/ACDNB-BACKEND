package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.usuario.*;
import com.teste.acdnb.core.domain.shared.valueobject.Email;
import com.teste.acdnb.core.domain.shared.valueobject.Nome;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.infrastructure.dto.PaginacaoResponse;
import com.teste.acdnb.infrastructure.dto.usuario.*;
import com.teste.acdnb.infrastructure.gateway.UsuarioRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for UsuarioController")
class UsuarioControllerTest {

    @Mock
    private AdicionarUsuarioUseCase adicionarUsuarioUseCase;

    @Mock
    private ListarUsuariosUseCase listarUsuariosUseCase;

    @Mock
    private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @Mock
    private RemoverUsuarioUseCase removerUsuarioUseCase;

    @Mock
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @Mock
    private BuscarUsuariosPorFiltroUseCase buscarUsuarioPorFiltroUseCase;

    @Mock
    private AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    @Mock
    private UsuarioRepositoryGateway usuarioRepositoryGateway;

    @InjectMocks
    private UsuarioController controller;

    // ==================== ADICIONAR USUARIO ====================

    @Test
    @DisplayName("POST /usuarios - Should return 200 and UsuarioResponseDTO when user is created successfully")
    void testAdicionarUsuarioSuccess() {
        UsuarioRequestDTO requestDTO = criarUsuarioRequest("João Silva", "joao@test.com", "senha123");
        UsuarioResponseDTO expectedResponse = criarUsuarioResponse(1, "João Silva", "joao@test.com");

        when(adicionarUsuarioUseCase.execute(requestDTO)).thenReturn(expectedResponse);

        ResponseEntity<UsuarioResponseDTO> response = controller.adicionarUsuario(requestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse.getId(), response.getBody().getId());
        assertEquals(expectedResponse.getNome(), response.getBody().getNome());

        verify(adicionarUsuarioUseCase, times(1)).execute(requestDTO);
    }

    // ==================== LISTAR USUARIOS ====================

    @Test
    @DisplayName("GET /usuarios - Should return 200 and list of usuarios")
    void testListarUsuariosSuccess() {
        Usuario usuario1 = criarUsuario(1, "João Silva", "joao@test.com");
        Usuario usuario2 = criarUsuario(2, "Maria Santos", "maria@test.com");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(listarUsuariosUseCase.execute()).thenReturn(usuarios);

        ResponseEntity<List<UsuarioListaDTO>> response = controller.listarUsuarios();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        verify(listarUsuariosUseCase, times(1)).execute();
    }

    @Test
    @DisplayName("GET /usuarios - Should return empty list when no usuarios exist")
    void testListarUsuariosEmpty() {
        when(listarUsuariosUseCase.execute()).thenReturn(List.of());

        ResponseEntity<List<UsuarioListaDTO>> response = controller.listarUsuarios();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());

        verify(listarUsuariosUseCase, times(1)).execute();
    }

    // ==================== BUSCAR USUARIO POR ID ====================

//    @Test
//    @DisplayName("GET /usuarios/{id} - Should return 200 and usuario when found")
//    void testBuscarUsuarioPorIdSuccess() {
//        Usuario usuario = criarUsuario(1, "João Silva", "joao@test.com");
//
//        when(buscarUsuarioPorIdUseCase.execute(1)).thenReturn(usuario);
//
//        ResponseEntity<UsuarioResponseDTO> response = controller.buscarUsuarioPorId(1);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(1, response.getBody().getId());
//        assertEquals("João Silva", response.getBody().getNome());
//
//        verify(buscarUsuarioPorIdUseCase, times(1)).execute(1);
//    }

    @Test
    @DisplayName("GET /usuarios/{id} - Should return 404 when usuario not found")
    void testBuscarUsuarioPorIdNotFound() {
        when(buscarUsuarioPorIdUseCase.execute(999)).thenReturn(null);

        ResponseEntity<UsuarioResponseDTO> response = controller.buscarUsuarioPorId(999);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(buscarUsuarioPorIdUseCase, times(1)).execute(999);
    }

    // ==================== DELETAR USUARIO ====================

    @Test
    @DisplayName("DELETE /usuarios/{id} - Should return 204 when usuario is deleted successfully")
    void testDeletarUsuarioSuccess() {
        doNothing().when(removerUsuarioUseCase).execute(1);

        ResponseEntity<Void> response = controller.deletarUsuario(1);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(removerUsuarioUseCase, times(1)).execute(1);
    }

    @Test
    @DisplayName("DELETE /usuarios/{id} - Should propagate exception when usuario not found")
    void testDeletarUsuarioNotFound() {
        doThrow(new RuntimeException("Usuario não encontrado")).when(removerUsuarioUseCase).execute(999);

        assertThrows(RuntimeException.class, () -> controller.deletarUsuario(999));

        verify(removerUsuarioUseCase, times(1)).execute(999);
    }

    // ==================== ATUALIZAR USUARIO ====================

    @Test
    @DisplayName("PUT /usuarios/{id} - Should return 200 and updated usuario")
    void testAtualizarUsuarioSuccess() {
        UsuarioRequestDTO requestDTO = criarUsuarioRequest("João Silva Updated", "joao@test.com", "newpass");
        UsuarioResponseDTO expectedResponse = criarUsuarioResponse(1, "João Silva Updated", "joao@test.com");

        when(atualizarUsuarioUseCase.execute(1, requestDTO)).thenReturn(expectedResponse);

        ResponseEntity<UsuarioResponseDTO> response = controller.atualizarUsuario(1, requestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("João Silva Updated", response.getBody().getNome());

        verify(atualizarUsuarioUseCase, times(1)).execute(1, requestDTO);
    }

    @Test
    @DisplayName("PUT /usuarios/{id} - Should propagate exception when usuario not found")
    void testAtualizarUsuarioNotFound() {
        UsuarioRequestDTO requestDTO = criarUsuarioRequest("João Silva", "joao@test.com", "senha123");

        when(atualizarUsuarioUseCase.execute(999, requestDTO))
                .thenThrow(new RuntimeException("Usuario não encontrado"));

        assertThrows(RuntimeException.class, () -> controller.atualizarUsuario(999, requestDTO));

        verify(atualizarUsuarioUseCase, times(1)).execute(999, requestDTO);
    }


    // ==================== HELPER METHODS ====================

    private UsuarioRequestDTO criarUsuarioRequest(String nome, String email, String senha) {
        return new UsuarioRequestDTO(
                nome,
                email,
                "(11) 91234-5678",
                LocalDate.of(1990, 1, 1),
                null,
                "Masculino",
                senha,
                "(11) 1234-5678",
                "USER",
                LocalDateTime.now(),
                1
        );
    }

    private UsuarioResponseDTO criarUsuarioResponse(int id, String nome, String email) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setEmail(email);
        dto.setCargo("USER");
        return dto;
    }

    private Usuario criarUsuario(int id, String nome, String email) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(Nome.of(nome));
        usuario.setEmail(Email.of(email));
        usuario.setCargo("USER");
        return usuario;
    }
}
