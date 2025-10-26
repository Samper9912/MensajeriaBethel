package com.jefferson.MensajeriaBethel.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para inicializar el cliente de SendGrid.
 * * Se obtiene la clave API de SendGrid desde las propiedades de Spring
 * (generalmente leídas desde application.properties o variables de entorno).
 */
@Configuration
public class SendGridConfig {

    // Inyecta el valor de la propiedad sendgrid.api-key.
    // Si usas una variable de entorno en tu entorno de despliegue, Spring Boot
    // la mapeará automáticamente si se llama SENDGRID_API_KEY.
    @Value("${sendgrid.api-key}")
    private String sendGridApiKey;

    /**
     * Define el bean de SendGrid.
     * Este objeto será inyectado en nuestro servicio de correo.
     * @return una instancia de SendGrid con la clave API configurada.
     */
    @Bean
    public SendGrid sendGridClient() {
        // Inicializa el cliente de SendGrid usando la clave API
        return new SendGrid(sendGridApiKey);
    }
}

