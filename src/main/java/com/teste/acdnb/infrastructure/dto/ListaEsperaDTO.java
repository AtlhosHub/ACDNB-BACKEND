package com.teste.acdnb.infrastructure.dto;

import com.teste.acdnb.core.domain.listaEspera.HorarioPreferencia;
import com.teste.acdnb.core.domain.shared.valueobject.*;
import com.teste.acdnb.core.domain.usuario.Usuario;

public record ListaEsperaDTO(String nome,
String email,
String dataInteresse,
String celular,
String nomeSocial,
String genero,
String dataNascimento,
String telefone,
String dataInclusao,
Integer usuarioInclusao,
String horarioPref
){
}
