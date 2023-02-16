package com.koldex.horticola.api.oauth.usuario;

import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Usuario {

    private final Long id;
    private final String nome;
    private final String cpf;
    private final String email;
    private final Set<PerfilEnum> perfils;

    Usuario(Jwt jwt){
        id =  Long.parseLong(jwt.getClaimAsString("id"));
        nome = jwt.getClaimAsString("nome");
        cpf = jwt.getClaimAsString("cpf");
        email = jwt.getClaimAsString("email");
        List<PerfilEnum> collect = Arrays.stream(jwt.getClaimAsString("roles").split(" ")).map(PerfilEnum::parse).toList();
        perfils = new HashSet<>(collect);
    }

}
