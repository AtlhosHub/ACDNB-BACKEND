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

public class AtualizarInteressadoUseCaseImpl implements AtualizarInteressadoUseCase {

    private final ListaEsperaGateway listaEsperaGateway;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;
    private final HorarioPreferenciaGateway horarioPreferenciaGateway;

    public AtualizarInteressadoUseCaseImpl(
            ListaEsperaGateway listaEsperaGateway,
            UsuarioRepository usuarioRepository,
            UsuarioEntityMapper usuarioEntityMapper,
            HorarioPreferenciaGateway horarioPreferenciaGateway
    ) {
        this.listaEsperaGateway = listaEsperaGateway;
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
        this.horarioPreferenciaGateway = horarioPreferenciaGateway;
    }

    @Override
    public ListaEspera execute(int id, ListaEsperaDTO dto) {
        ListaEspera interessado = listaEsperaGateway.buscarPorId(id);

        if (interessado == null) {
            throw new RuntimeException("Interessado não encontrado");
        }

        atualizarDomainComDTO(interessado, dto);

        return listaEsperaGateway.atualizarInteressado(interessado);
    }

    private void atualizarDomainComDTO(ListaEspera interessado, ListaEsperaDTO dto) {
        interessado.setNome(dto.nome() != null ? Nome.of(dto.nome()) : interessado.getNome());
        interessado.setEmail(dto.email() != null ? Email.of(dto.email()) : interessado.getEmail());
        interessado.setDataInteresse(dto.dataInteresse() != null ? DataInclusao.of(DateParser.parse(dto.dataInteresse())) : interessado.getDataInteresse());
        interessado.setCelular(dto.celular() != null ? Celular.of(dto.celular()) : interessado.getCelular());
        interessado.setNomeSocial(dto.nomeSocial() != null ? Nome.of(dto.nomeSocial()) : interessado.getNomeSocial());
        interessado.setGenero(dto.genero() != null ? dto.genero() : interessado.getGenero());
        interessado.setDataNascimento(dto.dataNascimento() != null ? DataInclusao.of(DateParser.parse(dto.dataNascimento())) : interessado.getDataNascimento());
        interessado.setTelefone(dto.telefone() != null ? Telefone.of(dto.telefone()) : interessado.getTelefone());
        interessado.setDataInclusao(dto.dataInclusao() != null ? DataInclusao.of(DateParser.parse(dto.dataInclusao())) : interessado.getDataInclusao());

        if (dto.usuarioInclusao() != null) {
            UsuarioEntity usuarioEntity = usuarioRepository.findById(dto.usuarioInclusao())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            interessado.setUsuarioInclusao(usuarioEntityMapper.toDomain(usuarioEntity));
        }

        if (dto.horarioPrefId() != null) {
            HorarioPreferencia horarioPrefExistente = horarioPreferenciaGateway.buscarPorId(dto.horarioPrefId());
            interessado.setHorarioPref(horarioPrefExistente);
        }
    }
}
