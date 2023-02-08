package com.koldex.horticola.api.oauth.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDTO {

    private String email;
    private String password;

}
