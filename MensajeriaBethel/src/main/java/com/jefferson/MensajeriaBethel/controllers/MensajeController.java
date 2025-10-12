package com.jefferson.MensajeriaBethel.controllers;

import com.jefferson.MensajeriaBethel.models.Mensaje;
import com.jefferson.MensajeriaBethel.services.MensajeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @PostMapping("/enviarMensaje")
    @ResponseBody
    public String enviarMensaje(
            @RequestParam String nombreRemitente,
            @RequestParam String emailDestinatario,
            @RequestParam String mensaje,
            @RequestParam String asunto
    ) {
        Mensaje m = new Mensaje(nombreRemitente, emailDestinatario, mensaje, asunto);
        mensajeService.enviarMensaje(m);
        return "ok";
    }


}
