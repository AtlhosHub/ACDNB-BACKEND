package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.exception.ResourceNotFoundException;
import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.application.exception.DataConflictException;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.core.domain.usuario.valueobject.Senha;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioRequestDTO;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioResponseDTO;

import java.util.Optional;

public class AdicionarUsuarioUseCaseImpl implements AdicionarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    public AdicionarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public UsuarioResponseDTO execute(UsuarioRequestDTO usuarioRequestDTO) {

        usuarioGateway.buscarUsuarioPorEmail(usuarioRequestDTO.email())
                .ifPresent(usuarioExistente -> {
                    throw new DataConflictException("E-mail de usuário já cadastrado");
                });

        Usuario usuarioInclusao = null;
        if (usuarioRequestDTO.usuarioInclusao() != null) {
            usuarioInclusao = usuarioGateway.buscarUsuarioPorId(usuarioRequestDTO.usuarioInclusao())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário de inclusão não encontrado"));
        }

        var usuarioParaRegistrar = new Usuario();
        usuarioParaRegistrar.setNome(Nome.of(usuarioRequestDTO.nome()));
        usuarioParaRegistrar.setEmail(Email.of(usuarioRequestDTO.email()));
        usuarioParaRegistrar.setSenha(Senha.of(usuarioRequestDTO.senha()));
        usuarioParaRegistrar.setCelular(Celular.of(usuarioRequestDTO.celular()));
        usuarioParaRegistrar.setDataNascimento(DataNascimento.of(usuarioRequestDTO.dataNascimento()));
        usuarioParaRegistrar.setNomeSocial(NomeSocial.of(usuarioRequestDTO.nomeSocial(), usuarioRequestDTO.nome()));
        usuarioParaRegistrar.setGenero(usuarioRequestDTO.genero());
        usuarioParaRegistrar.setTelefone(Telefone.of(usuarioRequestDTO.telefone()));
        usuarioParaRegistrar.setCargo(usuarioRequestDTO.cargo());
        usuarioParaRegistrar.setDataInclusao(DataInclusao.of(usuarioRequestDTO.dataInclusao()));
        usuarioParaRegistrar.setUsuarioInclusao(usuarioInclusao);

        Usuario usuarioCadastrado = usuarioGateway.adicionarUsuario(usuarioParaRegistrar);

        return new UsuarioResponseDTO(
                usuarioCadastrado.getNome().getValue(),
                usuarioCadastrado.getEmail().getValue(),
                usuarioCadastrado.getCelular().getValue(),
                usuarioCadastrado.getDataNascimento().getValue(),
                usuarioCadastrado.getNomeSocial().getValue(),
                usuarioCadastrado.getGenero(),
                usuarioCadastrado.getTelefone().getValue(),
                usuarioCadastrado.getCargo()
        );
    }
}
