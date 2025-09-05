package Ejercicio7;

/**
 * Clase que representa un curso individual en la plataforma
 *
 * Esta clase implementa equals() y hashCode() correctamente para que
 * LinkedHashSet pueda identificar cursos duplicados de manera eficiente.
 * Dos cursos son considerados iguales si tienen el mismo código único.
 */
class Curso {
    private String codigo;          // Identificador único del curso
    private String nombre;          // Nombre descriptivo del curso
    private String instructor;      // Nombre del instructor
    private int duracionHoras;      // Duración en horas
    private String categoria;       // Categoría temática

    /**
     * Constructor para crear un nuevo curso
     * @param codigo Identificador único (ej: "JAVA-101")
     * @param nombre Nombre del curso (ej: "Fundamentos de Java")
     * @param instructor Nombre del instructor
     * @param duracionHoras Duración total en horas
     * @param categoria Categoría del curso (ej: "Programación")
     */
    public Curso(String codigo, String nombre, String instructor, int duracionHoras, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracionHoras = duracionHoras;
        this.categoria = categoria;
    }

    // Métodos getter para acceder a las propiedades
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getInstructor() { return instructor; }
    public int getDuracionHoras() { return duracionHoras; }
    public String getCategoria() { return categoria; }

    /**
     * Método equals() - CRÍTICO para el funcionamiento de LinkedHashSet
     *
     * LinkedHashSet usa este método para determinar si dos cursos son duplicados.
     * Basamos la comparación únicamente en el código del curso, porque ese
     * es nuestro identificador único en el sistema.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                    // Misma referencia en memoria
        if (obj == null || getClass() != obj.getClass()) return false; // Tipos diferentes

        Curso curso = (Curso) obj;
        return codigo.equals(curso.codigo);              // Comparar por código único
    }

    /**
     * Método hashCode() - DEBE ser consistente con equals()
     *
     * LinkedHashSet usa hash codes para organizar elementos internamente.
     * Si dos objetos son equals(), deben tener el mismo hash code.
     */
    @Override
    public int hashCode() {
        return codigo.hashCode();  // Hash basado en el código único
    }

    /**
     * Representación en string del curso para facilitar la visualización
     */
    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%dh)",
                codigo, nombre, instructor, duracionHoras);
    }
}
