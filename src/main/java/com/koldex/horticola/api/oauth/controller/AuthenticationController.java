package com.koldex.horticola.api.oauth.controller;

import com.koldex.horticola.api.oauth.dto.AuthenticateDTO;
import com.koldex.horticola.api.oauth.dto.AuthenticationDTO;
import com.koldex.horticola.api.oauth.dto.RegisterDTO;
import com.koldex.horticola.api.oauth.service.AutheticationService;
import com.koldex.horticola.api.oauth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AutheticationService autheticationService;
    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationDTO> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(registerService.apply(registerDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationDTO> autheticate(@RequestBody AuthenticateDTO authenticateDTO) {
        return ResponseEntity.ok(autheticationService.apply(authenticateDTO));
    }

}
