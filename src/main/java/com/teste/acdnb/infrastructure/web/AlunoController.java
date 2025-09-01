package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.aluno.AdicionarAlunoUseCase;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
@SecurityRequirement(name = "Bearer")
@Tag(name = "AlunoController", description = "Endpoints para gerenciar os alunos no sistema")
public class AlunoController {
    private final AdicionarAlunoUseCase adicionarAlunoUseCase;

    public AlunoController(AdicionarAlunoUseCase adicionarAlunoUseCase) {
        this.adicionarAlunoUseCase = adicionarAlunoUseCase;
    }

    @PostMapping
    public ResponseEntity<Aluno> adicionarAluno(@RequestBody AlunoDTO aluno) {
        Aluno executar = adicionarAlunoUseCase.execute(aluno);
        return ResponseEntity.ok(executar);
    }
}
