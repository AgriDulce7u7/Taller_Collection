package Ejercicio7;

import java.util.LinkedHashSet;
import java.util.Iterator;

/**
 * Sistema de gestión de cursos favoritos usando LinkedHashSet
 *
 * Esta clase demuestra las ventajas clave de LinkedHashSet:
 * 1. Mantiene el orden de inserción (como LinkedList)
 * 2. Evita elementos duplicados (como HashSet)
 * 3. Operaciones eficientes O(1) para agregar, remover y buscar
 */
public class PlataformaCursosFavoritos {

    // LinkedHashSet mantiene orden de inserción Y evita duplicados
    private LinkedHashSet<Curso> cursosFavoritos;
    private int contadorOperaciones;  // Para numererar las operaciones en la demo

    /**
     * Constructor: inicializa el sistema de favoritos
     */
    public PlataformaCursosFavoritos() {
        this.cursosFavoritos = new LinkedHashSet<>();
        this.contadorOperaciones = 0;

        System.out.println("🎓 PLATAFORMA DE CURSOS FAVORITOS INICIALIZADA");
        System.out.println("Usando LinkedHashSet para mantener orden y evitar duplicados\n");
    }

    /**
     * Agrega un curso a favoritos, manteniendo orden y evitando duplicados
     * @param curso El curso a agregar a favoritos
     * @return true si se agregó exitosamente, false si ya existía
     */
    public boolean agregarAFavoritos(Curso curso) {
        contadorOperaciones++;
        System.out.println("📌 OPERACIÓN " + contadorOperaciones + ": Agregando a favoritos");
        System.out.println("   Curso: " + curso);

        // LinkedHashSet.add() retorna false si el elemento ya existe
        boolean agregado = cursosFavoritos.add(curso);

        if (agregado) {
            System.out.println("   ✅ AGREGADO - Nuevo favorito registrado");
        } else {
            System.out.println("   ❌ YA EXISTE - No se permiten duplicados");
            System.out.println("   💡 El orden original se mantiene intacto");
        }

        mostrarEstadoFavoritos();
        return agregado;
    }

    /**
     * Remueve un curso de favoritos
     * @param curso El curso a remover
     * @return true si se removió exitosamente, false si no existía
     */
    public boolean removerDeFavoritos(Curso curso) {
        contadorOperaciones++;
        System.out.println("🗑️ OPERACIÓN " + contadorOperaciones + ": Removiendo de favoritos");
        System.out.println("   Curso: " + curso);

        boolean removido = cursosFavoritos.remove(curso);

        if (removido) {
            System.out.println("   ✅ REMOVIDO - Ya no está en favoritos");
            System.out.println("   💡 El orden de los cursos restantes se preserva");
        } else {
            System.out.println("   ❌ NO ENCONTRADO - El curso no estaba en favoritos");
        }

        mostrarEstadoFavoritos();
        return removido;
    }

    /**
     * Verifica si un curso está en favoritos
     * @param curso El curso a verificar
     * @return true si está en favoritos, false si no
     */
    public boolean estaEnFavoritos(Curso curso) {
        return cursosFavoritos.contains(curso);
    }

    /**
     * Muestra el estado actual de los favoritos con orden preservado
     */
    private void mostrarEstadoFavoritos() {
        System.out.println("   📊 Estado actual de favoritos:");
        System.out.println("      Total de cursos: " + cursosFavoritos.size());

        if (cursosFavoritos.isEmpty()) {
            System.out.println("      Lista vacía");
        } else {
            System.out.println("      Orden de marcación (más antiguo → más reciente):");

            int posicion = 1;
            // LinkedHashSet mantiene el orden de inserción en el iterador
            for (Curso curso : cursosFavoritos) {
                System.out.println("      " + posicion + ". " + curso);
                posicion++;
            }
        }
        System.out.println("   " + "─".repeat(60) + "\n");
    }

    /**
     * Demuestra que el orden se mantiene incluso después de remociones
     */
    public void demostrarPreservacionDeOrden() {
        System.out.println("🧪 DEMOSTRACIÓN ESPECIAL: Preservación de orden tras remociones\n");

        // Crear cursos de prueba
        Curso cursoA = new Curso("WEB-201", "Desarrollo Web Avanzado", "Ana García", 40, "Web");
        Curso cursoB = new Curso("MOB-101", "Apps Móviles Android", "Luis Rodríguez", 35, "Móvil");
        Curso cursoC = new Curso("DATA-301", "Análisis de Datos", "María López", 50, "Data Science");
        Curso cursoD = new Curso("AI-401", "Inteligencia Artificial", "Dr. Chen", 60, "IA");

        // Agregar cursos en un orden específico
        System.out.println("➡️ Agregando cursos en orden: A → B → C → D");
        agregarAFavoritos(cursoA);
        agregarAFavoritos(cursoB);
        agregarAFavoritos(cursoC);
        agregarAFavoritos(cursoD);

        // Remover el curso del medio
        System.out.println("➡️ Removiendo curso B (del medio)");
        removerDeFavoritos(cursoB);

        // Agregar el curso B de nuevo
        System.out.println("➡️ Re-agregando curso B (debería ir al final)");
        agregarAFavoritos(cursoB);

        System.out.println("🎯 RESULTADO: A → C → D → B (B se movió al final)");
        System.out.println("💡 LinkedHashSet mantiene el orden basado en cuándo se insertó cada elemento\n");
    }

    /**
     * Obtiene el número total de cursos favoritos
     */
    public int getTotalFavoritos() {
        return cursosFavoritos.size();
    }

    /**
     * Método principal - Demostración completa del sistema
     */
    public static void main(String[] args) {
        // Crear instancia del sistema
        PlataformaCursosFavoritos plataforma = new PlataformaCursosFavoritos();

        // Crear cursos de ejemplo para las pruebas
        Curso java101 = new Curso("JAVA-101", "Fundamentos de Java", "Prof. Martinez", 30, "Programación");
        Curso python201 = new Curso("PY-201", "Python Intermedio", "Dra. Silva", 25, "Programación");
        Curso react301 = new Curso("REACT-301", "React Avanzado", "Ing. Torres", 40, "Frontend");
        Curso sql101 = new Curso("SQL-101", "Bases de Datos SQL", "Prof. Ramírez", 20, "Bases de Datos");
        Curso git201 = new Curso("GIT-201", "Control de Versiones", "Dev. Morales", 15, "Herramientas");

        System.out.println("🧪 PRUEBA 1: Agregando cursos iniciales (orden de marcación)");
        plataforma.agregarAFavoritos(java101);
        plataforma.agregarAFavoritos(python201);
        plataforma.agregarAFavoritos(react301);
        plataforma.agregarAFavoritos(sql101);

        System.out.println("🧪 PRUEBA 2: Intentos de duplicados (deben ser rechazados)");
        plataforma.agregarAFavoritos(java101);     // Duplicado
        plataforma.agregarAFavoritos(python201);   // Duplicado

        System.out.println("🧪 PRUEBA 3: Removiendo curso del medio");
        plataforma.removerDeFavoritos(python201);

        System.out.println("🧪 PRUEBA 4: Agregando nuevos cursos (van al final)");
        plataforma.agregarAFavoritos(git201);
        plataforma.agregarAFavoritos(python201);   // Re-agregar (va al final)

        System.out.println("🧪 PRUEBA 5: Verificación de existencia");
        System.out.println("¿Java101 en favoritos? " + plataforma.estaEnFavoritos(java101));
        System.out.println("¿Curso inexistente en favoritos? " +
                plataforma.estaEnFavoritos(new Curso("XXX-999", "No Existe", "Nadie", 0, "Ninguna")));
        System.out.println();

        // Demostración especial de preservación de orden
        PlataformaCursosFavoritos demo = new PlataformaCursosFavoritos();
        demo.demostrarPreservacionDeOrden();

        // Resumen final
        System.out.println("=".repeat(70));
        System.out.println("🎯 RESUMEN DE VENTAJAS DE LinkedHashSet:");
        System.out.println("✅ Mantiene el orden de inserción original");
        System.out.println("✅ Evita duplicados automáticamente");
        System.out.println("✅ Operaciones eficientes O(1) para agregar/remover/buscar");
        System.out.println("✅ Perfecto para listas de favoritos ordenadas y sin repetición");
        System.out.println("=".repeat(70));
    }
}
