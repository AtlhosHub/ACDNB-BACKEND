package com.teste.acdnb.core.application.usecase.aluno;

import com.teste.acdnb.core.application.gateway.AlunoGateway;
import com.teste.acdnb.core.domain.aluno.Aluno;
import com.teste.acdnb.infrastructure.dto.AlunoAniversarioDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ListarAniversariosUseCaseImpl implements ListarAniversariosUseCase {
    private final AlunoGateway alunoGateway;
    public ListarAniversariosUseCaseImpl(AlunoGateway alunoGateway) {
        this.alunoGateway = alunoGateway;
    }

    @Override
    public List<AlunoAniversarioDTO> execute() {
        int mesAtual = LocalDate.now().getMonthValue();
        List<Aluno> alunos = alunoGateway.listarAniversariantes().stream()
                .filter(a -> {
                    int mesNascimento = a.getDataNascimento().getValue().getMonthValue();
                    return mesNascimento >= mesAtual;
                }).toList();

        List<AlunoAniversarioDTO> aniversariantes = alunos.stream()
                .map(a -> new AlunoAniversarioDTO(a.getNome().getValue(), a.getDataNascimento().getValue()))
                .collect(Collectors.toList());
        return aniversariantes.isEmpty() ? List.of() : aniversariantes;
    }
}
