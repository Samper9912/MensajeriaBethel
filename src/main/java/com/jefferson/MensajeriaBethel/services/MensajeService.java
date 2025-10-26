package com.jefferson.MensajeriaBethel.services;

import com.jefferson.MensajeriaBethel.models.Mensaje;
import com.jefferson.MensajeriaBethel.repositories.MensajeRepository;
// NOTA: ELIMINAMOS LA LÍNEA DE IMPORTACIÓN ERRÓNEA porque ambas clases
// (MensajeService y SendGridEmailService) están en el mismo paquete.

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    // Ahora se resuelve porque SendGridEmailService está en esta misma carpeta/paquete
    private final SendGridEmailService sendGridEmailService;
    private final MensajeRepository mensajeRepository;

    public MensajeService(SendGridEmailService sendGridEmailService, MensajeRepository mensajeRepository) {
        this.sendGridEmailService = sendGridEmailService;
        this.mensajeRepository = mensajeRepository;
    }

    public void enviarMensaje(Mensaje mensaje) {
        // Guardar en base de datos
        mensajeRepository.save(mensaje);

        String asunto = mensaje.getNombreRemitente() + " - " + mensaje.getAsunto();

        String cuerpoHtml =
                "<html>" +
                        "<body style='font-family: Arial, sans-serif;'>" +
                        "<h2>Mensaje de contacto de Colegio Bethel</h2>" +
                        "<p><strong>De:</strong> " + mensaje.getNombreRemitente() + "</p>" +
                        "<p><strong>Email:</strong> " + mensaje.getEmailDestinatario() + "</p>" +
                        "<hr>" +
                        "<p>" + mensaje.getMensaje().replace("\n", "<br>") + "</p>" +
                        "</body>" +
                        "</html>";

        boolean exito = sendGridEmailService.sendEmail(
                mensaje.getEmailDestinatario(),
                asunto,
                cuerpoHtml
        );

        if (exito) {
            System.out.println("Correo enviado con éxito a: " + mensaje.getEmailDestinatario());
        } else {
            System.err.println("Error al intentar enviar el correo a: " + mensaje.getEmailDestinatario());
        }
    }

    public List<Mensaje> obtenerTodosMensajes() {
        return mensajeRepository.findAll();
    }
}