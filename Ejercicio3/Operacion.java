package Ejercicio3;


/**
 * CLASE ABSTRACTA OPERACION
 * Esta es la base para todas las operaciones del editor.
 * Cada operación debe saber cómo ejecutarse y cómo deshacerse.
 * Esto es una implementación del patrón Command.
 */
abstract class Operacion {
    protected String descripcion;

    public Operacion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método abstracto que cada operación específica debe implementar
    public abstract void ejecutar(Imagen imagen);

    // Método abstracto para deshacer la operación
    public abstract void deshacer(Imagen imagen);

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
