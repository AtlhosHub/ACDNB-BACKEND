package com.teste.acdnb.core.application.usecase.usuario;

import com.teste.acdnb.core.application.gateway.UsuarioGateway;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.usuario.Usuario;
import com.teste.acdnb.core.domain.usuario.valueobject.Senha;
import com.teste.acdnb.infrastructure.dto.usuario.UsuarioDTO;

public class AdicionarUsuarioUseCaseImpl implements AdicionarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    public AdicionarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Usuario execute(UsuarioDTO usuarioDTO) {
        // colocar as validações ai pae
        var usuarioParaRegistrar = new Usuario();
        usuarioParaRegistrar.setNome(Nome.of(usuarioDTO.nome()));
        usuarioParaRegistrar.setEmail(Email.of(usuarioDTO.email()));
        usuarioParaRegistrar.setSenha(Senha.of(usuarioDTO.senha()));
        usuarioParaRegistrar.setCelular(Celular.of(usuarioDTO.celular()));
        usuarioParaRegistrar.setDataNascimento(DataInclusao.of(usuarioDTO.dataNascimento()));
        usuarioParaRegistrar.setNomeSocial(Nome.of(usuarioDTO.nomeSocial()));
        usuarioParaRegistrar.setGenero(usuarioDTO.genero());
        usuarioParaRegistrar.setTelefone(Telefone.of(usuarioDTO.telefone()));
        usuarioParaRegistrar.setCargo(usuarioDTO.cargo());
        usuarioParaRegistrar.setDataInclusao(DataInclusao.of(usuarioDTO.dataInclusao()));
//        usuarioParaRegistrar.setUsuarioInclusao();
//                toDomain(entity.getUsuarioInclusao()),
//                toDomainList(entity.getUsuariosCadastrados())
        return usuarioGateway.adicionarUsuario(usuarioParaRegistrar);
    }
}
