package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.exception.DataConflictException;
import com.teste.acdnb.core.application.usecase.aluno.*;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.core.domain.shared.valueobject.Cpf;
import com.teste.acdnb.core.domain.shared.valueobject.Email;
import com.teste.acdnb.core.domain.shared.valueobject.Nome;
import com.teste.acdnb.infrastructure.dto.PaginacaoResponse;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoAniversarioDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoInfoDTO;
import com.teste.acdnb.infrastructure.filter.ListarAlunosMensalidadeFilter;
import com.teste.acdnb.infrastructure.persistence.jpa.mensalidade.MensalidadeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for AlunoController")
public class AlunoControllerTest {

    @Mock
    private AdicionarAlunoUseCase adicionarAlunoUseCase;
    @Mock
    private ListarAlunosUseCase listarAlunosUseCase;
    @Mock
    private DeletarAlunoUseCase deletarAlunoUseCase;
    @Mock
    private BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase;
    @Mock
    private AtualizarAlunoUseCase atualizarAlunoUseCase;
    @Mock
    private ListarAniversariosUseCase listarAniversariosUseCase;
    @Mock
    private QtdAlunosAtivosUseCase qtdAlunosAtivosUseCase;
    @Mock
    private ListarAlunosMensalidades listarAlunosMensalidades;
    @Mock
    private MensalidadeRepository mensalidadeRepository;

    @InjectMocks
    private AlunoController controller;

    // ==================== ADICIONAR ALUNO ====================

    @Test
    @DisplayName("POST /alunos - Should return 200 and Aluno when created successfully")
    void testAdicionarAlunoSuccess() {
        AlunoDTO alunoDTO = criarAlunoDTO("João Silva", "joao.silva@example.com", "123456789", "58165708031");
        Aluno alunoResponse = criarAluno(1, "João Silva", "joao.silva@example.com", "123456789", "58165708031");

        when(adicionarAlunoUseCase.execute(alunoDTO)).thenReturn(alunoResponse);

        ResponseEntity<Aluno> response = controller.adicionarAluno(alunoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alunoResponse.getId(), response.getBody().getId());
        assertEquals(alunoResponse.getNome(), response.getBody().getNome());
        verify(adicionarAlunoUseCase, times(1)).execute(alunoDTO);
    }

    @Test
    @DisplayName("POST /alunos - Should return 400 when request data is invalid")
    void testAdicionarAlunoBadRequest() {
        AlunoDTO alunoDTO = criarAlunoDTO("", "invalid-email", "123", "456"); // Invalid data

        when(adicionarAlunoUseCase.execute(alunoDTO))
                .thenThrow(new IllegalArgumentException("Dados inválidos"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.adicionarAluno(alunoDTO);
        });

        assertEquals("Dados inválidos", exception.getMessage());
        verify(adicionarAlunoUseCase, times(1)).execute(alunoDTO);
    }

    @Test
    @DisplayName("POST /alunos - Should return 409 when email, rg or cpf conflict occurs")
    void testAdicionarAlunoConflict() {
        AlunoDTO alunoDTO = criarAlunoDTO("João Silva", "joao.silva@example.com", "123456789", "987654321");

        doThrow(new DataConflictException("Conflito de email, rg ou cpf")).when(adicionarAlunoUseCase).execute(alunoDTO);

        Exception exception = assertThrows(DataConflictException.class, () -> {
            controller.adicionarAluno(alunoDTO);
        });

        assertEquals("Conflito de email, rg ou cpf", exception.getMessage());
        verify(adicionarAlunoUseCase, times(1)).execute(alunoDTO);
    }

    // ==================== LISTAR ALUNOS ====================

    @Test
    @DisplayName("GET /alunos - Should return 200 and list of alunos")
    void testListarAlunosSuccess() {
        Aluno aluno1 = criarAluno(1, "João Silva", "joao.silva@example.com", "123456789", "58165708031");
        Aluno aluno2 = criarAluno(2, "Maria Santos", "maria.santos@example.com", "987654321", "37145525020");
        when(listarAlunosUseCase.execute()).thenReturn(List.of(aluno1, aluno2));

        ResponseEntity<List<Aluno>> response = controller.listarAlunos();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(listarAlunosUseCase, times(1)).execute();
    }

    @Test
    @DisplayName("GET /alunos - Should return empty list when no alunos exist")
    void testListarAlunosEmpty() {
        when(listarAlunosUseCase.execute()).thenReturn(List.of());

        ResponseEntity<List<Aluno>> response = controller.listarAlunos();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    // ==================== BUSCAR ALUNO POR ID ====================
    
    @Test
    @DisplayName("GET /alunos/{id} - Should return 200 and AlunoInfoDTO when found")
    void testBuscarAlunoPorIdSuccess() {
        AlunoInfoDTO alunoResponse = criarAlunoInfoDTO(1, "João Silva");

        when(buscarAlunoPorIdUseCase.execute(1)).thenReturn(alunoResponse);

        ResponseEntity<AlunoInfoDTO> response = controller.buscarAlunoPorId(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().id());
        assertEquals("João Silva", response.getBody().nome());
        verify(buscarAlunoPorIdUseCase, times(1)).execute(1);
    }

    @Test
    @DisplayName("GET /alunos/{id} - Should return 404 when aluno not found")
    void testBuscarAlunoPorIdNotFound() {
        when(buscarAlunoPorIdUseCase.execute(999)).thenThrow(new IllegalArgumentException("Aluno não encontrado"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.buscarAlunoPorId(999);
        });

        assertEquals("Aluno não encontrado", exception.getMessage());

        verify(buscarAlunoPorIdUseCase, times(1)).execute(999);
    }

    // ==================== DELETAR ALUNO ====================

    @Test
    @DisplayName("DELETE /alunos/{id} - Should return 204 when aluno is deleted successfully")
    void testDeletarAlunoSuccess() {
        doNothing().when(deletarAlunoUseCase).execute(1);

        ResponseEntity<Void> response = controller.deletarAluno(1);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarAlunoUseCase, times(1)).execute(1);
    }

    @Test
    @DisplayName("DELETE /alunos/{id} - Should return 404 when aluno does not exist")
    void testDeletarAlunoNotFound() {
        doThrow(new IllegalArgumentException("Aluno não encontrado")).when(deletarAlunoUseCase).execute(999);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.deletarAluno(999);
        });

        assertEquals("Aluno não encontrado", exception.getMessage());

        verify(deletarAlunoUseCase, times(1)).execute(999);
    }

    // ==================== ATUALIZAR ALUNO ====================

    @Test
    @DisplayName("PUT /alunos/{id} - Should return 200 and updated aluno")
    void testAtualizarAlunoSuccess() {
        Aluno alunoRequest = criarAluno(1, "João Silva", "joao.silva@example.com", "123456789", "58165708031");
        Aluno alunoAtualizado = criarAluno(1, "João Silva Atualizado", "joao.silva@example.com", "123456789", "58165708031");

        when(atualizarAlunoUseCase.execute(alunoRequest, 1)).thenReturn(alunoAtualizado);

        ResponseEntity<Aluno> response = controller.atualizarAluno(alunoRequest, 1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("João Silva Atualizado", response.getBody().getNome().getValue());
        verify(atualizarAlunoUseCase, times(1)).execute(alunoRequest, 1);
    }

    @Test
    @DisplayName("PUT /alunos/{id} - Should return 404 when aluno to update does not exist")
    void testAtualizarAlunoNotFound() {
        Aluno alunoRequest = criarAluno(999, "Aluno Inexistente", "inexistente@example.com", "000000000", "58165708031");

        doThrow(new IllegalArgumentException("Aluno não encontrado")).when(atualizarAlunoUseCase).execute(alunoRequest, 999);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.atualizarAluno(alunoRequest, 999);
        });

        assertEquals("Aluno não encontrado", exception.getMessage());
        verify(atualizarAlunoUseCase, times(1)).execute(alunoRequest, 999);
    }

    @Test
    @DisplayName("PUT /alunos/{id} - Should return 409 when email, rg or cpf conflict occurs")
    void testAtualizarAlunoConflict() {
        Aluno alunoRequest = criarAluno(1, "João Silva", "joao.silva@example.com", "123456789", "58165708031");

        doThrow(new DataConflictException("Conflito de email, rg ou cpf")).when(atualizarAlunoUseCase).execute(alunoRequest, 1);

        Exception exception = assertThrows(DataConflictException.class, () -> {
            controller.atualizarAluno(alunoRequest, 1);
        });

        assertEquals("Conflito de email, rg ou cpf", exception.getMessage());
        verify(atualizarAlunoUseCase, times(1)).execute(alunoRequest, 1);
    }

    // ==================== LISTAR ANIVERSÁRIOS ====================

    @Test
    @DisplayName("GET /alunos/aniversariantes - Should return 200 and list of aniversariantes")
    void testListarAniversariosSuccess() {
        AlunoAniversarioDTO dto1 = criarAlunoAniversarioDTO("João Silva", LocalDate.of(2000, 12, 15));
        AlunoAniversarioDTO dto2 = criarAlunoAniversarioDTO("Maria Santos", LocalDate.of(1998, 12, 20));
        when(listarAniversariosUseCase.execute()).thenReturn(List.of(dto1, dto2));

        ResponseEntity<List<AlunoAniversarioDTO>> response = controller.listarAniversarios();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(listarAniversariosUseCase, times(1)).execute();
    }

    @Test
    @DisplayName("GET /alunos/aniversariantes - Should return empty list when no aniversariantes")
    void testListarAniversariosEmpty() {
        when(listarAniversariosUseCase.execute()).thenReturn(List.of());

        ResponseEntity<List<AlunoAniversarioDTO>> response = controller.listarAniversarios();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    // ==================== QUANTIDADE DE ALUNOS ATIVOS ====================

    @Test
    @DisplayName("GET /alunos/ativos - Should return 200 and qtd alunos ativos")
    void testQtdAlunosAtivosSuccess() {
        when(qtdAlunosAtivosUseCase.execute()).thenReturn(5);

        ResponseEntity<Integer> response = controller.qtdAlunosAtivos();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody());
        verify(qtdAlunosAtivosUseCase, times(1)).execute();
    }

    // ==================== LISTAR ALUNOS COM COMPROVANTES ====================

    @Test
    @DisplayName("POST /alunos/comprovantes - Should return 200 and paginated comprovantes")
    void testListarAlunosComComprovantesSuccess() {
        ListarAlunosMensalidadeFilter filtro = new ListarAlunosMensalidadeFilter(null, null, null, "2025-01-01", "2025-12-31", 0, 10);
        AlunoComprovanteDTO dto1 = criarAlunoComprovanteDTO(1, "João Silva");
        AlunoComprovanteDTO dto2 = criarAlunoComprovanteDTO(2, "Maria Santos");

        when(listarAlunosMensalidades.execute(filtro)).thenReturn(List.of(dto1, dto2));
        when(mensalidadeRepository.countAlunosComMensalidade(filtro.status(), filtro.nome(), LocalDate.parse(filtro.dataEnvioFrom()), LocalDate.parse(filtro.dataEnvioTo()))).thenReturn(2L);

        ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> response = controller.listarAlunosComComprovantes(filtro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().content().size());
        verify(listarAlunosMensalidades, times(1)).execute(filtro);
        verify(mensalidadeRepository, times(1)).countAlunosComMensalidade(filtro.status(), filtro.nome(), LocalDate.parse(filtro.dataEnvioFrom()), LocalDate.parse(filtro.dataEnvioTo()));
    }

    @Test
    @DisplayName("POST /alunos/comprovantes - Should return empty paginated response when no comprovantes found")
    void testListarAlunosComComprovantesEmpty() {
        ListarAlunosMensalidadeFilter filtro = new ListarAlunosMensalidadeFilter(null, null, null, "2025-01-01", "2025-12-31", 0, 10);

        when(listarAlunosMensalidades.execute(filtro)).thenReturn(List.of());
        when(mensalidadeRepository.countAlunosComMensalidade(filtro.status(), filtro.nome(), LocalDate.parse(filtro.dataEnvioFrom()), LocalDate.parse(filtro.dataEnvioTo()))).thenReturn(0L);

        ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> response = controller.listarAlunosComComprovantes(filtro);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().content().isEmpty());
        verify(listarAlunosMensalidades, times(1)).execute(filtro);
        verify(mensalidadeRepository, times(1)).countAlunosComMensalidade(filtro.status(), filtro.nome(), LocalDate.parse(filtro.dataEnvioFrom()), LocalDate.parse(filtro.dataEnvioTo()));
    }

    // ==================== HELPER METHODS ====================

    private Aluno criarAluno(Integer id, String nome, String email, String rg, String cpf) {
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome(Nome.of(nome));
        aluno.setEmail(Email.of(email));
        aluno.setRg(rg);
        aluno.setCpf(Cpf.of(cpf));
        return aluno;
    }

    private AlunoDTO criarAlunoDTO(String nome, String email, String rg, String cpf) {
        return new AlunoDTO(
            nome,
            email,
            LocalDate.of(2000, 1, 1),
            cpf,
            rg,
            null,
            null,
            null,
            null,
            null,
            null,
            "",
            "false",
            true,
            true,
            true,
            null,
            null,
            null
        );
    }

    private AlunoInfoDTO criarAlunoInfoDTO(Integer id, String nome) {
        return new AlunoInfoDTO(
            id,
            nome,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                true,
                null,
                true,
                null,
                null,
                null
        );
    }

    private AlunoAniversarioDTO criarAlunoAniversarioDTO(String nome, LocalDate dataNascimento) {
        return new AlunoAniversarioDTO(
                nome,
                dataNascimento
        );
    }

    private AlunoComprovanteDTO criarAlunoComprovanteDTO(Integer id, String nome) {
        return new AlunoComprovanteDTO(
                1,
                id,
                nome,
                true,
                null,
                null,
                "PAGO",
                "CARTAO",
                null,
                false,
                false
        );
    }
    
}
