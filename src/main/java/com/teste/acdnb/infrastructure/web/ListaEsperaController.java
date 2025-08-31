package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.listaEspera.AdicionarInteressadoUseCase;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.infrastructure.dto.ListaEsperaDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lista-espera")
@Tag(name = "ListaEsperaController", description = "Endpoints para gerenciar interessados na lista de espera")
public class ListaEsperaController {

    private final AdicionarInteressadoUseCase adicionarInteressadoUseCase;

    public ListaEsperaController(AdicionarInteressadoUseCase adicionarInteressadoUseCase) {
        this.adicionarInteressadoUseCase = adicionarInteressadoUseCase;
    }

    @PostMapping
    public ResponseEntity<ListaEspera> adicionarInteressado(@RequestBody ListaEsperaDTO interessado) {
        ListaEspera executar = adicionarInteressadoUseCase.execute(interessado);
        return ResponseEntity.ok(executar);
    }
}
