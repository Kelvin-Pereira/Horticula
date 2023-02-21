package com.koldex.horticola.api.z_exemples;

import com.koldex.horticola.config.user.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exemple")
@RequiredArgsConstructor
public class ExemplesController {

    @GetMapping
    public Usuario teste(Usuario usuario) {
        return usuario;
    }

}
