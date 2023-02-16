package com.koldex.horticola.config.security;

import com.koldex.horticola.api.oauth.entity.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] PERMITS = {"/auth/**"};


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(PERMITS)
                .permitAll()
                .requestMatchers(HttpMethod.GET).hasAuthority(RoleEnum.ROLE_USER.getRole())
                .requestMatchers(HttpMethod.POST).hasAuthority(RoleEnum.ROLE_USER.getRole())
                .requestMatchers(HttpMethod.PUT).hasAuthority(RoleEnum.ROLE_USER.getRole())
                .requestMatchers(HttpMethod.PATCH).hasAuthority(RoleEnum.ROLE_USER.getRole())
                .requestMatchers(HttpMethod.DELETE).hasAuthority(RoleEnum.ROLE_USER.getRole())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())

        return http.build();
    }

}
