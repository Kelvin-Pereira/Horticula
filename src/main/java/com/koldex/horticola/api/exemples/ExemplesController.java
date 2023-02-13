package com.koldex.horticola.api.exemples;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/exemple")
@RequiredArgsConstructor
public class ExemplesController {

    @GetMapping
    public String teste(){
        return "OK";
    }

}
