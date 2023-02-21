package com.koldex.horticola.api.oauth.dto;

import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String primeiroNome;
    private String sobreNome;
    private String cpf;
    private String email;
    private Set<PerfilEnum> perfils;
    private String password;

}
