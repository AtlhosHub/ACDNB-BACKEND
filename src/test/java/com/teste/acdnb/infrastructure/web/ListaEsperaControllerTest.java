package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.core.application.usecase.listaEspera.*;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.core.domain.shared.valueobject.DataInclusao;
import com.teste.acdnb.core.domain.shared.valueobject.DataNascimento;
import com.teste.acdnb.core.domain.shared.valueobject.Email;
import com.teste.acdnb.core.domain.shared.valueobject.Nome;
import com.teste.acdnb.infrastructure.dto.ListaEsperaDTO;
import com.teste.acdnb.infrastructure.dto.PaginacaoResponse;
import com.teste.acdnb.infrastructure.filter.InteressadosFilter;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for ListaEsperaController")
public class ListaEsperaControllerTest {

    @Mock
    private AdicionarInteressadoUseCase adicionarInteressadoUseCase;

    @Mock
    private ListarInteressadosUseCase listarInteressadosUseCase;

    @Mock
    private BuscarInteressadoUseCase buscarInteressadoUseCase;

    @Mock
    private DeletarInteressadoUseCase deletarInteressadoUseCase;

    @Mock
    private AtualizarInteressadoUseCase atualizarInteressadoUseCase;

    @Mock
    private ListaEsperaGateway listaEsperaGateway;

    @InjectMocks
    private ListaEsperaController controller;

    // ==================== ADICIONAR INTERESSADO ====================

    @Test
    @DisplayName("POST /interessados - Should return 200 and ListaEspera when interessado is created successfully")
    void testAdicionarInteressadoSuccess() {
        ListaEsperaDTO interessadoRequest = criarInteressadoDTO( "João Silva", "joao.silva@email.com");
        ListaEspera interessadoResponse = criarInteressado(1, "João Silva", "joao.silva@email.com");

        when(adicionarInteressadoUseCase.execute(interessadoRequest)).thenReturn(interessadoResponse);

        ResponseEntity<ListaEspera> response = controller.adicionarInteressado(interessadoRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(interessadoResponse.getId(), response.getBody().getId());
        assertEquals(interessadoResponse.getNome(), response.getBody().getNome());

        verify(adicionarInteressadoUseCase, times(1)).execute(interessadoRequest);
    }

    // ==================== LISTAR INTERESSADOS ====================

    @Test
    @DisplayName("GET /interessados - Should return 200 and list of interessados")
    void testListarTodosInteressadosSuccess() {
        InteressadosFilter filtro = new InteressadosFilter(0,10);
        ListaEspera interessado1 = criarInteressado(1, "João Silva", "joao.silva@email.com");
        ListaEspera interessado2 = criarInteressado(2, "Maria Santos", "maria.santos@email.com");
        when(listarInteressadosUseCase.execute(filtro)).thenReturn(List.of(interessado1, interessado2));
        when(listaEsperaGateway.listarTodos()).thenReturn(List.of(interessado1, interessado2));

        ResponseEntity<PaginacaoResponse<ListaEspera>> response = controller.listarTodosInteressados(filtro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().content().size());
        verify(listarInteressadosUseCase, times(1)).execute(filtro);
        verify(listaEsperaGateway, times(1)).listarTodos();
    }

    @Test
    @DisplayName("GET /interessados - Should return 200 and empty list when no interessados exist")
    void testListarTodosInteressadosEmpty() {
        InteressadosFilter filtro = new InteressadosFilter(0,10);
        when(listarInteressadosUseCase.execute(filtro)).thenReturn(List.of());
        when(listaEsperaGateway.listarTodos()).thenReturn(List.of());

        ResponseEntity<PaginacaoResponse<ListaEspera>> response = controller.listarTodosInteressados(filtro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().content().isEmpty());
        verify(listarInteressadosUseCase, times(1)).execute(filtro);
        verify(listaEsperaGateway, times(1)).listarTodos();
    }

    // ==================== BUSCAR INTERESSADO ====================

    @Test
    @DisplayName("GET /interessados/{id} - Should return 200 and interessado when found")
    void testBuscarInteressadoSuccess() {
        ListaEspera interessado = criarInteressado(1, "João Silva", "joao.silva@email.com");
        when(buscarInteressadoUseCase.execute(1)).thenReturn(interessado);

        ResponseEntity<ListaEspera> response = controller.buscarInteressado(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(interessado.getId(), response.getBody().getId());
        verify(buscarInteressadoUseCase, times(1)).execute(1);
    }

    @Test
    @DisplayName("GET /interessados/{id} - Should return 404 when interessado not found")
    void testBuscarInteressadoNotFound() {
        doThrow(new RuntimeException("Interessado não encontrado com id: 999"))
                .when(buscarInteressadoUseCase).execute(999);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.buscarInteressado(999);
        });

        assertEquals("Interessado não encontrado com id: 999", exception.getMessage());
        verify(buscarInteressadoUseCase, times(1)).execute(999);
    }

    // ==================== DELETAR INTERESSADO ====================

    @Test
    @DisplayName("DELETE /interessados/{id} - Should return 204 when interessado is deleted successfully")
    void testDeletarInteressadoSuccess() {
        doNothing().when(deletarInteressadoUseCase).execute(1);

        ResponseEntity<Void> response = controller.deletarInteressado(1);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarInteressadoUseCase, times(1)).execute(1);
    }

    @Test
    @DisplayName("DELETE /interessados/{id} - Should return 404 when interessado not found")
    void testDeletarInteressadoNotFound() {
        doThrow(new RuntimeException("Interessado não encontrado com id: 999"))
                .when(deletarInteressadoUseCase).execute(999);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.deletarInteressado(999);
        });

        assertEquals("Interessado não encontrado com id: 999", exception.getMessage());
        verify(deletarInteressadoUseCase, times(1)).execute(999);
    }

    // ==================== ATUALIZAR INTERESSADO ====================

    @Test
    @DisplayName("PUT /interessados/{id} - Should return 200 and atualizado when interessado is updated successfully")
    void testAtualizarInteressadoSuccess() {
        ListaEsperaDTO dto = criarInteressadoDTO("João Silva Atualizado", "joao.silva@email.com");
        ListaEspera atualizado = criarInteressado(1, "João Silva Atualizado", "joao.silva@email.com");
        when(atualizarInteressadoUseCase.execute(1, dto)).thenReturn(atualizado);

        ResponseEntity<ListaEspera> response = controller.atualizarInteressado(1, dto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("João Silva Atualizado", response.getBody().getNome().getValue());
        verify(atualizarInteressadoUseCase, times(1)).execute(1, dto);
    }

    @Test
    @DisplayName("PUT /interessados/{id} - Should return 404 when interessado not found")
    void testAtualizarInteressadoNotFound() {
        ListaEsperaDTO dto = criarInteressadoDTO("João Silva Atualizado", "joao.silva@email.com");

        doThrow(new RuntimeException("Interessado não encontrado"))
                .when(atualizarInteressadoUseCase).execute(999, dto);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.atualizarInteressado(999, dto);
        });

        assertEquals("Interessado não encontrado", exception.getMessage());
        verify(atualizarInteressadoUseCase, times(1)).execute(999, dto);
    }

    // ==================== HELPER METHODS ====================

    private ListaEspera criarInteressado(Integer id, String nome, String email) {
        return new ListaEspera(
                id,
                Nome.of(nome),
                Email.of(email),
                DataInclusao.of(LocalDateTime.now()),
                null,
                null,
                null,
                DataNascimento.of(LocalDate.of(1990, 1, 1)),
                null,
                DataInclusao.of(LocalDateTime.now()),
                null,
                null
        );
    }

    private ListaEsperaDTO criarInteressadoDTO(String nome, String email) {
        return new ListaEsperaDTO(
                nome,
                email,
                LocalDateTime.now().toString(),
                null,
                null,
                null,
                LocalDate.of(1990, 1, 1),
                null,
                LocalDateTime.now().toString(),
                null,
                null
        );
    }
}