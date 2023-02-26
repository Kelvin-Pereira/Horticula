package com.koldex.horticola.config.user;

import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;

import java.util.Set;

public sealed interface Usuario permits UsuarioPrincipal {

    Long getId();

    String getNome();

    String getCpf();

    String getEmail();

    Set<PerfilEnum> getPerfils();

}
