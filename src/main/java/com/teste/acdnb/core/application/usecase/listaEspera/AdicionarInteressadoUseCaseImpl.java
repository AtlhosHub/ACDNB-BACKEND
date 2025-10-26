package com.teste.acdnb.core.application.usecase.listaEspera;

import com.teste.acdnb.core.application.gateway.HorarioPreferenciaGateway;
import com.teste.acdnb.core.application.gateway.ListaEsperaGateway;
import com.teste.acdnb.core.domain.listaEspera.ListaEspera;
import com.teste.acdnb.core.domain.horarioPreferencia.HorarioPreferencia;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.infrastructure.dto.ListaEsperaDTO;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntity;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioEntityMapper;
import com.teste.acdnb.infrastructure.persistence.jpa.usuario.UsuarioRepository;
import com.teste.acdnb.infrastructure.util.DateParser;

public class AdicionarInteressadoUseCaseImpl implements AdicionarInteressadoUseCase {

    private final ListaEsperaGateway listaEsperaGateway;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;
    private final HorarioPreferenciaGateway horarioPreferenciaGateway;


    public AdicionarInteressadoUseCaseImpl(ListaEsperaGateway listaEsperaGateway, UsuarioRepository usuarioRepository, UsuarioEntityMapper usuarioEntityMapper, HorarioPreferenciaGateway horarioPreferenciaGateway) {
        this.listaEsperaGateway = listaEsperaGateway;
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
        this.horarioPreferenciaGateway = horarioPreferenciaGateway;
    }

    @Override
    public ListaEspera execute(ListaEsperaDTO dto) {

        var interessado = new ListaEspera();

        interessado.setNome(Nome.of(dto.nome()));
        interessado.setEmail(Email.of(dto.email()));
        interessado.setDataInteresse(dto.dataInteresse() != null ? DataInclusao.of(DateParser.parse(dto.dataInteresse())) : null);
        interessado.setCelular(Celular.of(dto.celular()));
        interessado.setNomeSocial(NomeSocial.of(dto.nomeSocial(), dto.nome()));
        interessado.setGenero(dto.genero());
        interessado.setDataNascimento(dto.dataNascimento() != null ? DataNascimento.of(dto.dataNascimento()) : null);
        interessado.setTelefone(Telefone.of(dto.telefone()));
        interessado.setDataInclusao(dto.dataInclusao() != null ? DataInclusao.of(DateParser.parse(dto.dataInclusao())) : null);

        if (dto.usuarioInclusao() != null) {
            UsuarioEntity usuarioEntity = usuarioRepository.findById(dto.usuarioInclusao())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            interessado.setUsuarioInclusao(usuarioEntityMapper.toDomain(usuarioEntity));
        }


        if (dto.horarioPrefId() != null) {
            HorarioPreferencia horarioPrefExistente = horarioPreferenciaGateway.buscarPorId(dto.horarioPrefId());
            interessado.setHorarioPref(horarioPrefExistente);
        }

        return listaEsperaGateway.adicionarInteressado(interessado);
    }
}