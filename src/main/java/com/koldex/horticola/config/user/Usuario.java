package com.koldex.horticola.config.user;

import com.koldex.horticola.api.oauth.entity.Perfil;
import com.koldex.horticola.api.oauth.entity.User;
import com.koldex.horticola.api.oauth.entity.enums.RoleEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Usuario {

    private final Long id;
    private final String nome;
    private final String cpf;
    private final String email;
    private final Set<RoleEnum> roles;

    Usuario(User user) {
        id = user.getIdUser();
        nome = user.getNomeCompleto();
        cpf = user.getCpf();
        email = user.getEmail();
        roles = user.getRole().stream().map(Perfil::getRole).collect(Collectors.toSet());
    }

}
