package com.koldex.horticola.config.user;

import com.koldex.horticola.api.oauth.entity.Perfil;
import com.koldex.horticola.api.oauth.entity.User;
import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Usuario {

    private final Long id;
    private final String nome;
    private final String cpf;
    private final String email;
    private final Set<PerfilEnum> perfils;

    Usuario(User user) {
        id = user.getIdUser();
        nome = user.getNomeCompleto();
        cpf = user.getCpf();
        email = user.getEmail();
        perfils = user.getPerfils().stream().map(Perfil::getPerfil).collect(Collectors.toSet());
    }

}
