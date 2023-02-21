package com.koldex.horticola.api.oauth.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PerfilEnum {

    ADMIN,
    USER;

    public static PerfilEnum parse(String role) {
        return PerfilEnum.valueOf(role.trim());
    }
}
