package com.jefferson.MensajeriaBethel.controllers;

import com.jefferson.MensajeriaBethel.models.Mensaje;
import com.jefferson.MensajeriaBethel.services.MensajeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    // Método existente para enviar mensaje
    @PostMapping("/enviarMensaje")
    @ResponseBody
    public String enviarMensaje(
            @RequestParam String nombreRemitente,
            @RequestParam String emailDestinatario,
            @RequestParam String mensaje,
            @RequestParam String asunto,
            @RequestParam String nombreTrabajador
    ) {
        Mensaje m = new Mensaje(nombreRemitente, emailDestinatario, mensaje, asunto, nombreTrabajador);
        mensajeService.enviarMensaje(m);
        return "ok";
    }

    // NUEVO: Mostrar todos los mensajes
    @GetMapping("/Bandeja")
    public String verMensajes(Model model) {
        List<Mensaje> mensajes = mensajeService.obtenerTodosMensajes();
        model.addAttribute("mensajes", mensajes);
        return "Bandeja"; // el nombre de la plantilla Thymeleaf
    }
}
