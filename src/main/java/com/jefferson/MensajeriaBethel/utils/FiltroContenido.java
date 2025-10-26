package com.jefferson.MensajeriaBethel.utils; // <-- ¡ESTE ES EL PAQUETE QUE DEBES CREAR!

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase estática de utilidad para validar contenido de texto.
 * Se usa para verificar si un mensaje o asunto contiene palabras prohibidas,
 * incluyendo las que usan reemplazo de caracteres (leetspeak).
 */
public class FiltroContenido {

    // Lista de palabras prohibidas (usar Set para búsquedas O(1) rápidas).
    private static final Set<String> PALABRAS_PROHIBIDAS = new HashSet<>(Arrays.asList(
            "puta", "mierda", "cabron", "malparido", "idiota", "imbecil",
            "estupido", "perro", "pene", "vagina", "culo", "teta", "caca", "pito", "verga"
    ));

    /**
     * Normaliza el texto de entrada aplicando conversiones de leetspeak.
     * Ejemplo: "p3rr0" -> "perro "
     */
    private static String normalizarTexto(String texto) {
        if (texto == null) return "";
        return texto
                .toLowerCase()
                // Reemplazos de leetspeak (convierte números/símbolos a letras)
                .replace('3', 'e')
                .replace('4', 'a')
                .replace('@', 'a')
                .replace('0', 'o')
                .replace('1', 'i')
                .replace('7', 't')
                .replace('5', 's')
                .replace('$', 's')
                // Reemplaza cualquier cosa que no sea letra o número con un espacio para separar las palabras
                .replaceAll("[^a-z0-9]", " ");
    }

    /**
     * Verifica si el texto contiene alguna grosería después de ser normalizado.
     * Es el método que llamarás desde el Controlador.
     * @param texto El texto a validar (asunto o mensaje).
     * @return true si se encuentra una palabra prohibida, false si es limpio.
     */
    public static boolean contieneGroserias(String texto) {
        String textoNormalizado = normalizarTexto(texto);

        // Divide el texto normalizado en palabras individuales por espacios
        String[] palabras = textoNormalizado.split("\\s+");

        for (String palabra : palabras) {
            // Limpia la palabra de cualquier residuo de caracteres no deseados
            String palabraLimpia = palabra.trim().replaceAll("[^a-z]", "");

            // Comprueba si la palabra limpia existe en nuestra lista de prohibidas
            if (PALABRAS_PROHIBIDAS.contains(palabraLimpia) && !palabraLimpia.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}