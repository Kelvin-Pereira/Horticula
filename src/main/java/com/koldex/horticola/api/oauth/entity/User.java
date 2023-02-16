package com.koldex.horticola.api.oauth.entity;

import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue
    private Long idUser;
    @Size(max = 20)
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private PerfilEnum role;

//    @OneToMany(mappedBy = "_user")
//    private Set<Perfil> perfils = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(perfils.stream().map(p -> p.getRole().name()).collect(Collectors.joining(","))));
//    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNomeCompleto() {
        return firstName + " " + lastName;
    }

}
