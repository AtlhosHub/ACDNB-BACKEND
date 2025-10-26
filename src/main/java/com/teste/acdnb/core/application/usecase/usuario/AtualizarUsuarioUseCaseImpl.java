package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.exception.DataConflictException;
import com.teste.acdnb.core.application.exception.ResourceNotFoundException;
import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.core.domain.usuario.valueobject.Senha;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioRequestDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioResponseDTO;

public class AtualizarUsuarioUseCaseImpl implements AtualizarUsuarioUseCase {
    private UsuarioGateway usuarioGateway;
    public AtualizarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public UsuarioResponseDTO execute(int id, UsuarioRequestDTO usuarioEditado) {
        Usuario usuarioExistente = usuarioGateway.buscarUsuarioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuarioGateway.buscarUsuarioPorEmail(usuarioEditado.email())
                .filter(u -> u.getId() != id)
                .ifPresent(u -> {
                    throw new DataConflictException("E-mail de usuário já cadastrado");
                });

        usuarioExistente.setNome(Nome.of(usuarioEditado.nome()));
        usuarioExistente.setEmail(Email.of(usuarioEditado.email()));
        usuarioExistente.setSenha(Senha.of(usuarioEditado.senha()));
        usuarioExistente.setCelular(Celular.of(usuarioEditado.celular()));
        usuarioExistente.setDataNascimento(DataNascimento.of(usuarioEditado.dataNascimento()));
        usuarioExistente.setNomeSocial(NomeSocial.of(usuarioEditado.nomeSocial(), usuarioEditado.nome()));
        usuarioExistente.setGenero(usuarioEditado.genero());
        usuarioExistente.setTelefone(Telefone.of(usuarioEditado.telefone()));
        usuarioExistente.setCargo(usuarioEditado.cargo());

        Usuario usuarioAtualizado = usuarioGateway.atualizarUsuario(usuarioExistente);

        System.out.println(usuarioAtualizado);

        return new UsuarioResponseDTO(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getNome().getValue(),
                usuarioAtualizado.getEmail().getValue(),
                usuarioAtualizado.getCelular().getValue(),
                usuarioAtualizado.getDataNascimento().getValue(),
                usuarioAtualizado.getNomeSocial().getValue(),
                usuarioAtualizado.getGenero(),
                usuarioAtualizado.getTelefone().getValue(),
                usuarioAtualizado.getCargo()
        );
    }
}
