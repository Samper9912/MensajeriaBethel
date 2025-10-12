package com.jefferson.MensajeriaBethel.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreRemitente;

    @Column(nullable = false, length = 100)
    private String emailDestinatario;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false, length = 150)
    private String asunto;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    // Constructor vacío
    public Mensaje() {}

    // Constructor con parámetros
    public Mensaje(String nombreRemitente, String emailDestinatario, String mensaje, String asunto) {
        this.nombreRemitente = nombreRemitente;
        this.emailDestinatario = emailDestinatario;
        this.mensaje = mensaje;
        this.asunto = asunto;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreRemitente() { return nombreRemitente; }
    public void setNombreRemitente(String nombreRemitente) { this.nombreRemitente = nombreRemitente; }

    public String getEmailDestinatario() { return emailDestinatario; }
    public void setEmailDestinatario(String emailDestinatario) { this.emailDestinatario = emailDestinatario; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
