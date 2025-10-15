package com.jefferson.MensajeriaBethel.services;

import com.jefferson.MensajeriaBethel.models.Mensaje;
import com.jefferson.MensajeriaBethel.repositories.MensajeRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    private final JavaMailSender mailSender;
    private final MensajeRepository mensajeRepository;

    public MensajeService(JavaMailSender mailSender, MensajeRepository mensajeRepository) {
        this.mailSender = mailSender;
        this.mensajeRepository = mensajeRepository;
    }

    public void enviarMensaje(Mensaje mensaje) {
        // Guardar en base de datos
        mensajeRepository.save(mensaje);

        // Crear email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(mensaje.getEmailDestinatario());
        email.setFrom("buzonfisitecnolcolegiobethel@gmail.com"); // tu correo fijo
        email.setSubject(mensaje.getNombreRemitente() + " - " + mensaje.getAsunto());
        email.setText("De: " + mensaje.getNombreRemitente() + "\n\n" + mensaje.getMensaje());

        // Enviar email
        mailSender.send(email);
    }
    public List<Mensaje> obtenerTodosMensajes() {
        return mensajeRepository.findAll();
    }


}
