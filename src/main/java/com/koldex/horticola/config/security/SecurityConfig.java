package com.koldex.horticola.config.security;

import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;
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

    private static final String[] PERMITS = {"/auth/authenticate"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(PERMITS)
                .permitAll()
                .requestMatchers(HttpMethod.GET).hasAnyAuthority(PerfilEnum.ROLE_USER.toString(), PerfilEnum.ROLE_ADMIN.toString() )
                .requestMatchers("/auth/regiser").hasAnyAuthority(PerfilEnum.ROLE_ADMIN.toString())
                .requestMatchers(HttpMethod.POST).hasAnyAuthority(PerfilEnum.ROLE_USER.toString())
                .requestMatchers(HttpMethod.PUT).hasAnyAuthority(PerfilEnum.ROLE_USER.toString())
                .requestMatchers(HttpMethod.PATCH).hasAnyAuthority(PerfilEnum.ROLE_USER.toString())
                .requestMatchers(HttpMethod.DELETE).hasAnyAuthority(PerfilEnum.ROLE_USER.toString())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

}
