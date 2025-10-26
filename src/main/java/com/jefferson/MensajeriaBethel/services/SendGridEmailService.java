package com.jefferson.MensajeriaBethel.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Servicio encargado de la comunicación con la API de SendGrid
 * para el envío de correos electrónicos transaccionales.
 * * Este servicio debe estar en el paquete 'services' para que Spring lo detecte.
 */
@Service
public class SendGridEmailService {

    // Cliente SendGrid inyectado desde la configuración (SendGridConfig)
    private final SendGrid sendGrid;

    // Email remitente fijo, leído desde application.properties (o variable de entorno)
    // Usamos el email que mencionaste en el entorno: buzonfisitecnolcolegiobethel@gmail.com
    @Value("${sendgrid.from-email:buzonfisitecnolcolegiobethel@gmail.com}")
    private String fromEmail;

    public SendGridEmailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    /**
     * Envía un correo electrónico a un destinatario específico.
     * * @param toEmail Email del destinatario real.
     *
     * @param subject     Asunto del correo.
     * @param htmlContent Contenido del cuerpo del mensaje en formato HTML.
     * @return true si el envío fue exitoso, false en caso de error.
     */
    public boolean sendEmail(String toEmail, String subject, String htmlContent) {
        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);

        Content content = new Content("text/html", htmlContent);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            // Envía la solicitud a SendGrid
            Response response = sendGrid.api(request);

            // Códigos 200 y 202 indican éxito
            if (response.getStatusCode() == 200 || response.getStatusCode() == 202) {
                return true;
            } else {
                System.err.println("SendGrid API Response Error. Status: " + response.getStatusCode());
                System.err.println("Body: " + response.getBody());
                return false;
            }
        } catch (IOException ex) {
            System.err.println("Error de I/O al enviar correo con SendGrid: " + ex.getMessage());
            return false;
        }
    }
}