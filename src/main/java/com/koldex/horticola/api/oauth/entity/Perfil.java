package com.koldex.horticola.api.oauth.entity;

import com.koldex.horticola.api.oauth.entity.enums.PerfilEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_perfil")
public class Perfil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long idPerfil;

    @Enumerated(EnumType.STRING)
    private PerfilEnum role;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

}
