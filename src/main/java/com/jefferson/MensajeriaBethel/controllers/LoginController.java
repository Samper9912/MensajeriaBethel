package com.jefferson.MensajeriaBethel.controllers;

import com.jefferson.MensajeriaBethel.models.Admin;
import com.jefferson.MensajeriaBethel.repositories.AdminRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AdminRepository adminRepository;

    public LoginController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/LoginPrivadoAdmin")
    public String mostrarLogin() {
        return "Login"; // Devuelve Login.html
    }

    @PostMapping("/LoginPrivadoAdmin")
    public String validarLogin(
            @RequestParam String usuario,
            @RequestParam String contrasena,
            Model model
    ) {
        // Busca admin en la tabla
        Admin admin = adminRepository.findByUsuarioAndContrasena(usuario, contrasena).orElse(null);

        if(admin != null) {
            // Login correcto -> redirige a la vista de mensajes
            return "redirect:/Bandeja";
        } else {
            // Login incorrecto -> vuelve al login con mensaje de error
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "Login";
        }
    }
}
