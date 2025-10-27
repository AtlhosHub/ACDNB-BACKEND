package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.core.application.usecase.listaEspera.*;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.dto.ListaEsperaDTO;
import com.teste.acdnb.infrastructure.dto.PaginacaoResponse;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.filter.InteressadosFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lista-espera")
@Tag(name = "ListaEsperaController", description = "Endpoints para gerenciar interessados na lista de espera")
public class ListaEsperaController {

    private final AdicionarInteressadoUseCase adicionarInteressadoUseCase;
    private final ListarInteressadosUseCase listarInteressadosUseCase;
    private final BuscarInteressadoUseCase buscarInteressadoUseCase;
    private final DeletarInteressadoUseCase deletarInteressadoUseCase;
    private final AtualizarInteressadoUseCase atualizarInteressadoUseCase;
    private final ListaEsperaGateway listaEsperaGateway;

    public ListaEsperaController(
            AdicionarInteressadoUseCase adicionarInteressadoUseCase,
            ListarInteressadosUseCase listarInteressadosUseCase,
            BuscarInteressadoUseCase buscarInteressadoUseCase,
            DeletarInteressadoUseCase deletarInteressadoUseCase,
            AtualizarInteressadoUseCase atualizarInteressadoUseCase,
            ListaEsperaGateway listaEsperaGateway) {
        this.adicionarInteressadoUseCase = adicionarInteressadoUseCase;
        this.listarInteressadosUseCase = listarInteressadosUseCase;
        this.buscarInteressadoUseCase = buscarInteressadoUseCase;
        this.deletarInteressadoUseCase = deletarInteressadoUseCase;
        this.atualizarInteressadoUseCase = atualizarInteressadoUseCase;
        this.listaEsperaGateway = listaEsperaGateway;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ListaEspera> adicionarInteressado(@RequestBody ListaEsperaDTO interessado) {
        ListaEspera executar = adicionarInteressadoUseCase.execute(interessado);
        return ResponseEntity.ok(executar);
    }

    @PostMapping
    public ResponseEntity<PaginacaoResponse<ListaEspera>> listarTodosInteressados(@RequestBody InteressadosFilter filtro) {
        List<ListaEspera> interessados = listarInteressadosUseCase.execute(filtro);
        int qtdInteressados = listaEsperaGateway.listarTodos().size();

        PaginacaoResponse<ListaEspera> response = new PaginacaoResponse<>(interessados, filtro.offset(), filtro.limit(), qtdInteressados);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaEspera> buscarInteressado(@PathVariable int id) {
        ListaEspera interessado = buscarInteressadoUseCase.execute(id);
        return ResponseEntity.ok(interessado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInteressado(@PathVariable int id) {
        deletarInteressadoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaEspera> atualizarInteressado(
            @PathVariable int id,
            @RequestBody ListaEsperaDTO dto
    ) {
        ListaEspera atualizado = atualizarInteressadoUseCase.execute(id, dto);
        return ResponseEntity.ok(atualizado);
    }

}
