package com.koldex.horticola.api.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String primeiroNome;
    private String sobreNome;
    private String cpf;
    private String email;
    private String password;

}
