package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.aluno.*;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoInfoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@SecurityRequirement(name = "Bearer")
@Tag(name = "AlunoController", description = "Endpoints para gerenciar os alunos no sistema")
public class AlunoController {
    private final AdicionarAlunoUseCase adicionarAlunoUseCase;
    private final ListarAlunosUseCase listarAlunosUseCase;
    private final DeletarAlunoUseCase deletarAlunoUseCase;
    private final BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase;
    private final AtualizarAlunoUseCase atualizarAlunoUseCase;

    public AlunoController(AdicionarAlunoUseCase adicionarAlunoUseCase, ListarAlunosUseCase listarAlunosUseCase, BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase, AtualizarAlunoUseCase atualizarAlunoUseCase, DeletarAlunoUseCase deletarAlunoUseCase) {
        this.adicionarAlunoUseCase = adicionarAlunoUseCase;
        this.listarAlunosUseCase = listarAlunosUseCase;
        this.buscarAlunoPorIdUseCase = buscarAlunoPorIdUseCase;
        this.atualizarAlunoUseCase = atualizarAlunoUseCase;
        this.deletarAlunoUseCase = deletarAlunoUseCase;
    }

    @PostMapping
    public ResponseEntity<Aluno> adicionarAluno(@Valid @RequestBody AlunoDTO aluno) {
        Aluno executar = adicionarAlunoUseCase.execute(aluno);
        return ResponseEntity.ok(executar);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos(){
        List<Aluno> executar = listarAlunosUseCase.execute();
        return ResponseEntity.ok(executar.isEmpty() ? List.of() : executar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoInfoDTO> buscarAlunoPorId(@PathVariable int id){
        AlunoInfoDTO aluno = buscarAlunoPorIdUseCase.execute(id);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable int id){
        deletarAlunoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Aluno> atualizarAluno(@Valid @RequestBody AlunoDTO aluno, @PathVariable int id){
        Aluno atualizar = adicionarAlunoUseCase.execute(aluno);
        return ResponseEntity.ok(atualizar);
    }
}
