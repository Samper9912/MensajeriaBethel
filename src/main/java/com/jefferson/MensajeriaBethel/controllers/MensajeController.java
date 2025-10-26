package com.jefferson.MensajeriaBethel.controllers;

import com.jefferson.MensajeriaBethel.models.Mensaje;
import com.jefferson.MensajeriaBethel.services.MensajeService;
import com.jefferson.MensajeriaBethel.utils.FiltroContenido;
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

    // Método para manejar el envío de mensajes desde el formulario
    @PostMapping("/enviarMensaje")
    @ResponseBody
    public String enviarMensaje(
            @RequestParam String nombreRemitente,
            @RequestParam String emailDestinatario,
            @RequestParam String mensaje,
            @RequestParam String asunto,
            @RequestParam String nombreTrabajador // Asumo que este campo también viene del formulario
    ) {
        // === VALIDACIÓN DE GROSERÍAS ===
        if (FiltroContenido.contieneGroserias(asunto) || FiltroContenido.contieneGroserias(mensaje)) {
            // Devuelve un mensaje de error que el frontend debe manejar
            return "ERROR: El mensaje o el asunto contiene lenguaje no apropiado (groserías).";
        }

        // === CREACIÓN Y ENVÍO DEL MENSAJE ===
        Mensaje m = new Mensaje(nombreRemitente, emailDestinatario, mensaje, asunto, nombreTrabajador);

        try {
            mensajeService.enviarMensaje(m);
            return "ok"; // Respuesta de éxito
        } catch (Exception e) {
            // Si hay un error en el servicio (ej. fallo de la API de SendGrid),
            // registramos el error y devolvemos una respuesta de fallo.
            System.err.println("Error al procesar el envío: " + e.getMessage());
            return "ERROR: Fallo interno al enviar el correo. Inténtalo de nuevo.";
        }
    }

    // Método para mostrar todos los mensajes en la bandeja
    @GetMapping("/Bandeja")
    public String verMensajes(Model model) {
        List<Mensaje> mensajes = mensajeService.obtenerTodosMensajes();
        model.addAttribute("mensajes", mensajes);
        return "Bandeja"; // el nombre de la plantilla Thymeleaf (o la que uses)
    }
}