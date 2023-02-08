package com.koldex.horticola.api.oauth.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    private String token;

}
