package Ejercicio4;

/**
 * Enumeración que define los niveles de severidad de los tickets
 * Cada nivel tiene un valor numérico para facilitar la comparación
 */
enum Severidad {
    CRITICA(4, "Crítica"),
    ALTA(3, "Alta"),
    MEDIA(2, "Media"),
    BAJA(1, "Baja");

    private final int prioridad;
    private final String nombre;

    Severidad(int prioridad, String nombre) {
        this.prioridad = prioridad;
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getNombre() {
        return nombre;
    }
}

