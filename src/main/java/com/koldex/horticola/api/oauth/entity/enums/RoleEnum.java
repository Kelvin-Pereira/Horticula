package com.koldex.horticola.api.oauth.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {

    ROLE_ADMIN,
    ROLE_USER;

    public static RoleEnum parse(String role) {
        return RoleEnum.valueOf(role.trim());
    }
}
