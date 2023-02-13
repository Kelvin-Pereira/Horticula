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
                .requestMatchers(HttpMethod.GET).hasAnyRole(RoleEnum.ROLE_USER.getRole())
                .requestMatchers(HttpMethod.POST).hasAnyRole(RoleEnum.ROLE_USER.getRole())
                .requestMatchers(HttpMethod.PUT).hasAnyRole(RoleEnum.ROLE_USER.getRole())
                .requestMatchers(HttpMethod.PATCH).hasAnyRole(RoleEnum.ROLE_USER.getRole())
                .requestMatchers(HttpMethod.DELETE).hasAnyRole(RoleEnum.ROLE_USER.getRole())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
