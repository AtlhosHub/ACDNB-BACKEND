package com.teste.acdnb.infrastructure.web;


import com.teste.acdnb.core.application.usecase.horarioPreferencia.*;
import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.infrastructure.dto.HorarioPreferenciaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario-preferencia")
public class HorarioPreferenciaController {

    private final AdicionarHorarioPreferenciaUseCase adicionarHorarioPreferenciaUseCase;
    private final ListarHorarioPreferenciaUseCase listarHorarioPreferenciaUseCase;
    private final BuscarHorarioPreferenciaPorIdUseCase buscarHorarioPreferenciaPorIdUseCase;
    private final AtualizarHorarioPreferenciaUseCase atualizarHorarioPreferenciaUseCase;
    private final DeletarHorarioPreferenciaUseCase deletarHorarioPreferenciaUseCase;

    public HorarioPreferenciaController(AdicionarHorarioPreferenciaUseCase adicionarHorarioPreferenciaUseCase,
                                        ListarHorarioPreferenciaUseCase listarHorarioPreferenciaUseCase,
                                        BuscarHorarioPreferenciaPorIdUseCase buscarHorarioPreferenciaPorIdUseCase,
                                        AtualizarHorarioPreferenciaUseCase atualizarHorarioPreferenciaUseCase,
                                        DeletarHorarioPreferenciaUseCase deletarHorarioPreferenciaUseCase) {
        this.adicionarHorarioPreferenciaUseCase = adicionarHorarioPreferenciaUseCase;
        this.listarHorarioPreferenciaUseCase = listarHorarioPreferenciaUseCase;
        this.buscarHorarioPreferenciaPorIdUseCase = buscarHorarioPreferenciaPorIdUseCase;
        this.atualizarHorarioPreferenciaUseCase = atualizarHorarioPreferenciaUseCase;
        this.deletarHorarioPreferenciaUseCase = deletarHorarioPreferenciaUseCase;
    }

    @PostMapping
    public ResponseEntity<HorarioPreferencia> adicionarHorarioPreferencia(@RequestBody HorarioPreferenciaDTO dto) {
        HorarioPreferencia salvo = adicionarHorarioPreferenciaUseCase.execute(dto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<HorarioPreferencia>> listarTodos() {
        List<HorarioPreferencia> lista = listarHorarioPreferenciaUseCase.execute();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioPreferencia> buscarPorId(@PathVariable Integer id) {
        HorarioPreferencia horario = buscarHorarioPreferenciaPorIdUseCase.execute(id);
        return ResponseEntity.ok(horario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioPreferencia> atualizarHorarioPreferencia(
            @PathVariable Integer id,
            @RequestBody HorarioPreferenciaDTO dto
    ) {
        HorarioPreferencia atualizado = atualizarHorarioPreferenciaUseCase.execute(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHorarioPreferencia(@PathVariable Integer id) {
        deletarHorarioPreferenciaUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

