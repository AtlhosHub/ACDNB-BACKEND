package com.teste.acdnb.infrastructure.web;

import com.teste.acdnb.core.application.usecase.aluno.*;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.PaginacaoResponse;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoAniversarioDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoComprovanteDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoDTO;
import com.teste.acdnb.infrastructure.dto.aluno.AlunoInfoDTO;
import com.teste.acdnb.infrastructure.filter.AlunoFilter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    private final ListarAniversariosUseCase listarAniversariosUseCase;
    private final QtdAlunosAtivosUseCase qtdAlunosAtivosUseCase;
    private final ListarAlunosMensalidades listarAlunosMensalidades;
    private final VerificarEmailCadastradoUseCase verificarEmailCadastradoUseCase;

    public AlunoController(AdicionarAlunoUseCase adicionarAlunoUseCase, ListarAlunosUseCase listarAlunosUseCase, BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase, AtualizarAlunoUseCase atualizarAlunoUseCase, DeletarAlunoUseCase deletarAlunoUseCase, ListarAniversariosUseCase listarAniversariosUseCase, QtdAlunosAtivosUseCase qtdAlunosAtivosUseCase, ListarAlunosMensalidades listarAlunosMensalidades, VerificarEmailCadastradoUseCase verificarEmailCadastradoUseCase) {
        this.adicionarAlunoUseCase = adicionarAlunoUseCase;
        this.listarAlunosUseCase = listarAlunosUseCase;
        this.buscarAlunoPorIdUseCase = buscarAlunoPorIdUseCase;
        this.atualizarAlunoUseCase = atualizarAlunoUseCase;
        this.deletarAlunoUseCase = deletarAlunoUseCase;
        this.listarAniversariosUseCase = listarAniversariosUseCase;
        this.qtdAlunosAtivosUseCase = qtdAlunosAtivosUseCase;
        this.listarAlunosMensalidades = listarAlunosMensalidades;
        this.verificarEmailCadastradoUseCase = verificarEmailCadastradoUseCase;
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

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@RequestBody Aluno aluno, @PathVariable int id){
        Aluno atualizar = atualizarAlunoUseCase.execute(aluno, id);
        return ResponseEntity.ok(atualizar);
    }

    @GetMapping("/aniversariantes")
    public ResponseEntity<List<AlunoAniversarioDTO>> listarAniversarios() {
        List<AlunoAniversarioDTO> aniversariantes = listarAniversariosUseCase.execute();
        return aniversariantes.isEmpty() ? ResponseEntity.ok(List.of()) : ResponseEntity.ok(aniversariantes);
    }

    @GetMapping("/ativos")
    public ResponseEntity<Integer> qtdAlunosAtivos(){
        return ResponseEntity.ok(qtdAlunosAtivosUseCase.execute());
    }

    @PostMapping("/comprovantes")
    public ResponseEntity<PaginacaoResponse<AlunoComprovanteDTO>> listarAlunosComComprovantes(@RequestBody AlunoFilter filtro) {
        List<AlunoComprovanteDTO> alunosComComprovantes = listarAlunosMensalidades.execute(filtro);
        int qtdAlunos = listarAlunosUseCase.execute().size();

        PaginacaoResponse<AlunoComprovanteDTO> response = new PaginacaoResponse<>(alunosComComprovantes, filtro.offset(), filtro.limit(), qtdAlunos);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/emailCadastrado")
    public ResponseEntity<Aluno> verificarEmailCadastrado(@RequestParam String email) {
        Optional<Aluno> aluno = verificarEmailCadastradoUseCase.execute(email);
        return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
