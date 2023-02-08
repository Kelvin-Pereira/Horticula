package com.koldex.horticola.api.oauth.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
