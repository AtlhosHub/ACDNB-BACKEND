package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.aluno.AdicionarAlunoUseCase;
import com.teste.acdnb.core.application.usecase.aluno.ListarAlunosUseCase;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;
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

    public AlunoController(AdicionarAlunoUseCase adicionarAlunoUseCase, ListarAlunosUseCase listarAlunosUseCase) {
        this.adicionarAlunoUseCase = adicionarAlunoUseCase;
        this.listarAlunosUseCase = listarAlunosUseCase;
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
}
