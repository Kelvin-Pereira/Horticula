package com.koldex.horticola.api.oauth.service;

import com.koldex.horticola.api.oauth.dto.AuthenticateDTO;
import com.koldex.horticola.api.oauth.dto.AuthenticationDTO;
import com.koldex.horticola.api.oauth.dto.RegisterDTO;
import com.koldex.horticola.api.oauth.entity.Perfil;
import com.koldex.horticola.api.oauth.entity.User;
import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;
import com.koldex.horticola.api.oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AutheticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // TODO refatorar !
    @Transactional
    public AuthenticationDTO register(RegisterDTO registerDTO) {
        Set<Perfil> collectionPerfil = new HashSet<>();
        Perfil perfil = new Perfil();
        Perfil perfil2 = new Perfil();
        perfil.setRole(PerfilEnum.ROLE_USER);
        perfil2.setRole(PerfilEnum.ROLE_ADMIN);
        collectionPerfil.add(perfil);
        collectionPerfil.add(perfil2);
        var user = User.builder()
                .firstName(registerDTO.getPrimeiroNome())
                .lastName(registerDTO.getSobreNome())
                .cpf(registerDTO.getCpf())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(collectionPerfil)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationDTO authetication(AuthenticateDTO authenticateDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateDTO.getEmail(),
                        authenticateDTO.getPassword())
        );
        var user = repository.findByEmailIgnoreCase(authenticateDTO.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationDTO.builder()
                .token(jwtToken)
                .build();
    }
}
