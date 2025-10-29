package com.jefferson.MensajeriaBethel.controllers;

import com.jefferson.MensajeriaBethel.models.Mensaje;
import com.jefferson.MensajeriaBethel.services.MensajeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// @Controller es crucial: marca esta clase como un controlador de vistas que devuelve nombres de plantillas.
@Controller
public class ViewController {

    private final MensajeService mensajeService;

    // Inyección de dependencia de MensajeService
    public ViewController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    /**
     * Mapea la ruta raíz (o /home) a la plantilla "Home".
     * Si tu archivo se llama Home.html y está en templates/, esto lo sirve.
     */
    @GetMapping("/")
    public String home() {
        return "Home"; // Devuelve el nombre de la plantilla HTML (Home.html)
    }

    /**
     * Mapea la ruta /Bandeja a la plantilla "Bandeja".
     */
    @GetMapping("/Bandeja")
    public String verMensajes(Model model) {
        List<Mensaje> mensajes = mensajeService.obtenerTodosMensajes();
        model.addAttribute("mensajes", mensajes);
        return "Bandeja"; // Devuelve el nombre de la plantilla HTML (Bandeja.html)
    }
}
