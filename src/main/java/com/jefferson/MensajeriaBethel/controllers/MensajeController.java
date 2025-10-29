package com.jefferson.MensajeriaBethel.controllers;

import com.jefferson.MensajeriaBethel.models.Mensaje;
import com.jefferson.MensajeriaBethel.services.MensajeService;
import com.jefferson.MensajeriaBethel.utils.FiltroContenido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit; // Importamos para simular retraso si es necesario

// @RestController es crucial: marca esta clase como un controlador de API que devuelve JSON.
@RestController
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    /**
     * Endpoint para guardar y enviar un mensaje. Recibe un objeto Mensaje en formato JSON.
     * Retorna un JSON compatible con el JS de SweetAlert2.
     */
    @PostMapping("/enviarMensaje")
    public ResponseEntity<Map<String, Object>> enviarMensaje(@RequestBody Mensaje mensaje) {
        Map<String, Object> response = new HashMap<>();

        // === 1. VALIDACIÓN DE GROSERÍAS ===
        boolean contieneGroseria = FiltroContenido.contieneGroserias(mensaje.getAsunto()) ||
                FiltroContenido.contieneGroserias(mensaje.getMensaje());

        response.put("contieneGroseria", contieneGroseria);

        if (contieneGroseria) {
            response.put("message", "El mensaje o el asunto contiene lenguaje no permitido.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // === 2. ENVÍO DEL MENSAJE ===
        try {
            // Simulación de retraso (opcional)
            // TimeUnit.SECONDS.sleep(1);

            boolean exito = mensajeService.enviarMensaje(mensaje);

            if (exito) {
                response.put("message", "El mensaje ha sido guardado y enviado con éxito.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "Fallo al enviar el correo. Inténtalo de nuevo.");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            System.err.println("Error al procesar el envío: " + e.getMessage());
            response.put("message", "Fallo interno del servidor. Contacta al administrador.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
