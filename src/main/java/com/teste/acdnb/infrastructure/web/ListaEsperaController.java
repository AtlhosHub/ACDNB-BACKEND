package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.listaEspera.AdicionarInteressadoUseCase;
import com.teste.acdnb.core.application.usecase.listaEspera.ListarInteressadosUseCase;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.dto.ListaEsperaDTO;
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


    public ListaEsperaController(
            AdicionarInteressadoUseCase adicionarInteressadoUseCase,
            ListarInteressadosUseCase listarInteressadosUseCase
    ) {
        this.adicionarInteressadoUseCase = adicionarInteressadoUseCase;
        this.listarInteressadosUseCase = listarInteressadosUseCase;
    }

    @PostMapping
    public ResponseEntity<ListaEspera> adicionarInteressado(@RequestBody ListaEsperaDTO interessado) {
        ListaEspera executar = adicionarInteressadoUseCase.execute(interessado);
        return ResponseEntity.ok(executar);
    }

    @GetMapping
    public ResponseEntity<List<ListaEspera>> listarTodosInteressados() {
        List<ListaEspera> lista = listarInteressadosUseCase.execute();
        return ResponseEntity.ok(lista);
    }

}
