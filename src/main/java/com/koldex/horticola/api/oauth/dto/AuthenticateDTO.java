package com.koldex.horticola.api.oauth.dto;

import com.koldex.horticola.api.util.type.Cpf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDTO {

    private Cpf cpf;
    private String password;

}
