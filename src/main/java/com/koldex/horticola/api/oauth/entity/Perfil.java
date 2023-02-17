package com.koldex.horticola.api.oauth.entity;

import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_perfil")
public class Perfil {

    @Id
    @GeneratedValue
    private Long idPerfil;
    @Enumerated(EnumType.STRING)
    private PerfilEnum role;

    @ManyToOne
    @JoinColumn(name = "id_User")
    private User user;

}
