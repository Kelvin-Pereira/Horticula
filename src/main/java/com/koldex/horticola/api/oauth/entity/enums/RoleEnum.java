package com.koldex.horticola.api.oauth.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {

    ROLE_ADMIN("Administrador"),
    ROLE_USER("Usuario");

    private final String role;
}
