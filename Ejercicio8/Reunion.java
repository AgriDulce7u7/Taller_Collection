package Ejercicio8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Reunion que implementa Comparable para permitir ordenación automática
 * en estructuras de datos como TreeSet.
 *
 * El criterio de comparación es la fecha-hora, permitiendo orden cronológico natural.
 */
class Reunion implements Comparable<Reunion> {
    private LocalDateTime fechaHora;
    private String asunto;

    // Constructor que inicializa una reunión con fecha-hora y asunto
    public Reunion(LocalDateTime fechaHora, String asunto) {
        this.fechaHora = fechaHora;
        this.asunto = asunto;
    }

    // Getters para acceder a los atributos privados
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getAsunto() {
        return asunto;
    }

    /**
     * Implementación del método compareTo de la interfaz Comparable.
     * Este método es CRUCIAL porque define cómo se ordenan las reuniones.
     *
     * Retorna:
     * - Número negativo si esta reunión es anterior a la otra
     * - Cero si son en el mismo momento (aunque esto sería raro)
     * - Número positivo si esta reunión es posterior a la otra
     */
    @Override
    public int compareTo(Reunion otra) {
        return this.fechaHora.compareTo(otra.fechaHora);
    }

    /**
     * Sobrescribimos equals para mantener consistencia con compareTo.
     * Dos reuniones son iguales si tienen la misma fecha-hora.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reunion reunion = (Reunion) obj;
        return fechaHora.equals(reunion.fechaHora);
    }

    /**
     * Sobrescribimos hashCode para mantener consistencia con equals.
     * Importante para el correcto funcionamiento en colecciones hash.
     */
    @Override
    public int hashCode() {
        return fechaHora.hashCode();
    }

    /**
     * Representación en cadena de texto de la reunión para facilitar la visualización.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("[%s] %s", fechaHora.format(formatter), asunto);
    }
}
