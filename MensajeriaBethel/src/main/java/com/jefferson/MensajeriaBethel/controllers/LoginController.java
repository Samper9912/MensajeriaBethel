package com.jefferson.MensajeriaBethel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/LoginPrivadoAdmin")
    public String mostrarLogin() {
        // Devuelve la vista Login.html
        return "Login";
    }
}
