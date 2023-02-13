package com.koldex.horticola.api.oauth.dto;

import lombok.*;

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
