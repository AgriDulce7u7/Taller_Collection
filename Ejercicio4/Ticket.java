package Ejercicio4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Ticket que implementa Comparable para definir el orden natural
 * de los tickets en la cola de prioridad
 */
class Ticket implements Comparable<Ticket> {
    private String id;
    private String descripcion;
    private Severidad severidad;
    private LocalDateTime fechaCreacion;

    public Ticket(String id, String descripcion, Severidad severidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Constructor alternativo que permite establecer la fecha manualmente (útil para pruebas)
    public Ticket(String id, String descripcion, Severidad severidad, LocalDateTime fechaCreacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Implementación del método compareTo de la interfaz Comparable
     * Define el orden natural de los tickets:
     * 1. Primero por severidad (mayor severidad tiene mayor prioridad)
     * 2. En caso de empate, por fecha de creación (más antigua primero)
     */
    @Override
    public int compareTo(Ticket otro) {
        // Comparar por severidad primero (orden descendente - mayor severidad primero)
        int comparacionSeveridad = Integer.compare(otro.severidad.getPrioridad(),
                this.severidad.getPrioridad());

        // Si las severidades son iguales, comparar por fecha (orden ascendente - más antigua primero)
        if (comparacionSeveridad == 0) {
            return this.fechaCreacion.compareTo(otro.fechaCreacion);
        }

        return comparacionSeveridad;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Severidad getSeveridad() {
        return severidad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Ticket[ID=%s, Severidad=%s, Fecha=%s, Descripción=%s]",
                id, severidad.getNombre(), fechaCreacion.format(formatter), descripcion);
    }
}
