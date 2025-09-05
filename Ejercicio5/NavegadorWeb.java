package Ejercicio5;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Navegador web simplificado que implementa la funcionalidad de navegación
 * adelante/atrás usando dos pilas (ArrayDeque) para mantener el historial.
 *
 * CONCEPTO FUNDAMENTAL:
 * Un navegador real mantiene dos historiales separados:
 * 1. Páginas visitadas hacia atrás desde la página actual
 * 2. Páginas que podemos visitar hacia adelante (solo si hemos ido hacia atrás)
 */
public class NavegadorWeb {

    // Pila que almacena las páginas visitadas hacia atrás desde la página actual
    // El tope de la pila representa la página inmediatamente anterior
    private ArrayDeque<String> historialAtras;

    // Pila que almacena las páginas que podemos visitar hacia adelante
    // Se llena solo cuando navegamos hacia atrás, y se vacía al visitar una nueva página
    private ArrayDeque<String> historialAdelante;

    // La página web actual en la que nos encontramos
    private String paginaActual;

    /**
     * Constructor que inicializa el navegador con una página de inicio
     */
    public NavegadorWeb() {
        this.historialAtras = new ArrayDeque<>();
        this.historialAdelante = new ArrayDeque<>();
        this.paginaActual = null;
        System.out.println("=== NAVEGADOR WEB SIMPLIFICADO ===");
        System.out.println("Navegador iniciado. Visita tu primera página.\n");
    }

    /**
     * Visita una nueva página web.
     *
     * LÓGICA IMPORTANTE:
     * 1. Si hay una página actual, la movemos al historial "atrás"
     * 2. Establecemos la nueva página como actual
     * 3. LIMPIAMOS completamente el historial "adelante" (comportamiento clave del navegador)
     *
     * ¿Por qué limpiamos el historial adelante?
     * Imagina que estás en: A -> B -> C, luego vas atrás a B.
     * El historial sería: atrás=[A], actual=B, adelante=[C]
     * Si ahora visitas una nueva página D desde B, el camino A->B->D es la nueva realidad,
     * y ya no tiene sentido poder ir "adelante" a C.
     */
    public void visitarPagina(String url) {
        // Validación básica de entrada
        if (url == null || url.trim().isEmpty()) {
            System.out.println("Error: URL no válida");
            return;
        }

        url = url.trim();

        // Si ya hay una página actual, la guardamos en el historial "atrás"
        if (paginaActual != null) {
            historialAtras.push(paginaActual);
            System.out.println("Guardando '" + paginaActual + "' en historial atrás");
        }

        // Establecemos la nueva página como actual
        paginaActual = url;

        // PUNTO CLAVE: Limpiamos el historial "adelante" porque hemos creado una nueva rama de navegación
        if (!historialAdelante.isEmpty()) {
            System.out.println("Limpiando historial adelante (ya no es válido tras visitar nueva página)");
            historialAdelante.clear();
        }

        System.out.println("📄 Visitando: " + paginaActual);
        mostrarEstado();
    }

    /**
     * Navega hacia atrás en el historial.
     *
     * FUNCIONAMIENTO:
     * 1. Tomamos la página más reciente del historial "atrás"
     * 2. Movemos la página actual al historial "adelante"
     * 3. La página del historial "atrás" se convierte en la actual
     */
    public boolean irAtras() {
        // Verificamos si es posible ir hacia atrás
        if (historialAtras.isEmpty()) {
            System.out.println("❌ No se puede ir atrás: no hay páginas en el historial");
            return false;
        }

        // Movemos la página actual al historial "adelante" para poder regresar después
        if (paginaActual != null) {
            historialAdelante.push(paginaActual);
            System.out.println("Moviendo '" + paginaActual + "' al historial adelante");
        }

        // Tomamos la página más reciente del historial "atrás"
        paginaActual = historialAtras.pop();

        System.out.println("⬅️ Navegando atrás a: " + paginaActual);
        mostrarEstado();
        return true;
    }

    /**
     * Navega hacia adelante en el historial.
     *
     * FUNCIONAMIENTO:
     * 1. Tomamos la página más reciente del historial "adelante"
     * 2. Movemos la página actual al historial "atrás"
     * 3. La página del historial "adelante" se convierte en la actual
     *
     * NOTA: Solo es posible si previamente hemos navegado hacia atrás
     */
    public boolean irAdelante() {
        // Verificamos si es posible ir hacia adelante
        if (historialAdelante.isEmpty()) {
            System.out.println("❌ No se puede ir adelante: no hay páginas disponibles");
            return false;
        }

        // Movemos la página actual al historial "atrás"
        if (paginaActual != null) {
            historialAtras.push(paginaActual);
            System.out.println("Moviendo '" + paginaActual + "' al historial atrás");
        }

        // Tomamos la página más reciente del historial "adelante"
        paginaActual = historialAdelante.pop();

        System.out.println("➡️ Navegando adelante a: " + paginaActual);
        mostrarEstado();
        return true;
    }

    /**
     * Muestra la página web actual
     */
    public String getPaginaActual() {
        return paginaActual;
    }

    /**
     * Verifica si es posible navegar hacia atrás
     */
    public boolean puedeIrAtras() {
        return !historialAtras.isEmpty();
    }

    /**
     * Verifica si es posible navegar hacia adelante
     */
    public boolean puedeIrAdelante() {
        return !historialAdelante.isEmpty();
    }

    /**
     * Muestra el estado actual del navegador de forma visual.
     * Esto nos ayuda a entender cómo se organizan los historiales.
     */
    private void mostrarEstado() {
        System.out.println("\n--- ESTADO ACTUAL DEL NAVEGADOR ---");

        // Mostramos el historial "atrás" (desde el más reciente al más antiguo)
        System.out.print("Historial ATRÁS: [");
        if (historialAtras.isEmpty()) {
            System.out.print("vacío");
        } else {
            // Creamos una representación visual del historial sin modificar las pilas originales
            ArrayDeque<String> temp = new ArrayDeque<>(historialAtras);
            boolean primero = true;
            while (!temp.isEmpty()) {
                if (!primero) System.out.print(" <- ");
                System.out.print(temp.pop());
                primero = false;
            }
        }
        System.out.println("]");

        // Mostramos la página actual
        System.out.println("Página ACTUAL: " + (paginaActual != null ? paginaActual : "ninguna"));

        // Mostramos el historial "adelante"
        System.out.print("Historial ADELANTE: [");
        if (historialAdelante.isEmpty()) {
            System.out.print("vacío");
        } else {
            ArrayDeque<String> temp = new ArrayDeque<>(historialAdelante);
            boolean primero = true;
            while (!temp.isEmpty()) {
                if (!primero) System.out.print(" -> ");
                System.out.print(temp.pop());
                primero = false;
            }
        }
        System.out.println("]");

        // Indicamos qué acciones están disponibles
        System.out.println("Puede ir atrás: " + (puedeIrAtras() ? "Sí" : "No"));
        System.out.println("Puede ir adelante: " + (puedeIrAdelante() ? "Sí" : "No"));
        System.out.println("------------------------------------\n");
    }

    /**
     * Muestra el historial completo de navegación de forma ordenada
     */
    public void mostrarHistorialCompleto() {
        System.out.println("\n=== HISTORIAL COMPLETO DE NAVEGACIÓN ===");

        if (historialAtras.isEmpty() && paginaActual == null && historialAdelante.isEmpty()) {
            System.out.println("No hay historial de navegación");
            return;
        }

        // Mostramos las páginas en orden cronológico de navegación
        ArrayDeque<String> tempAtras = new ArrayDeque<>(historialAtras);
        ArrayDeque<String> ordenado = new ArrayDeque<>();

        // Invertimos el historial atrás para mostrarlo en orden cronológico
        while (!tempAtras.isEmpty()) {
            ordenado.push(tempAtras.pop());
        }

        int posicion = 1;
        // Páginas del historial atrás (más antiguas primero)
        while (!ordenado.isEmpty()) {
            System.out.println(posicion + ". " + ordenado.pop());
            posicion++;
        }

        // Página actual
        if (paginaActual != null) {
            System.out.println(posicion + ". " + paginaActual + " ← (ACTUAL)");
            posicion++;
        }

        // Páginas del historial adelante
        ArrayDeque<String> tempAdelante = new ArrayDeque<>(historialAdelante);
        while (!tempAdelante.isEmpty()) {
            System.out.println(posicion + ". " + tempAdelante.pop() + " (disponible hacia adelante)");
            posicion++;
        }

        System.out.println("========================================\n");
    }

    /**
     * Método principal que demuestra el funcionamiento del navegador
     * con un escenario de prueba interactivo
     */
    public static void main(String[] args) {
        NavegadorWeb navegador = new NavegadorWeb();
        Scanner scanner = new Scanner(System.in);

        // Demostración automática del comportamiento clave
        System.out.println("=== DEMOSTRACIÓN AUTOMÁTICA ===\n");

        // Visitamos varias páginas secuencialmente
        navegador.visitarPagina("https://google.com");
        navegador.visitarPagina("https://stackoverflow.com");
        navegador.visitarPagina("https://github.com");
        navegador.visitarPagina("https://oracle.com");

        // Navegamos hacia atrás varias veces
        System.out.println("\n--- Navegando hacia atrás ---");
        navegador.irAtras(); // Volvemos a github.com
        navegador.irAtras(); // Volvemos a stackoverflow.com

        // Ahora podemos ir tanto atrás como adelante
        System.out.println("\n--- Navegando hacia adelante ---");
        navegador.irAdelante(); // Avanzamos a github.com

        // CASO CRÍTICO: Visitamos una nueva página, lo que debe limpiar el historial adelante
        System.out.println("\n--- CASO CRÍTICO: Visitando nueva página (limpia historial adelante) ---");
        navegador.visitarPagina("https://jetbrains.com");

        // Intentamos ir adelante (debería fallar porque el historial adelante se limpió)
        System.out.println("\n--- Intentando ir adelante después de visitar nueva página ---");
        navegador.irAdelante();

        navegador.mostrarHistorialCompleto();

        // Menú interactivo para que el usuario experimente
        System.out.println("\n=== MODO INTERACTIVO ===");
        System.out.println("Comandos disponibles:");
        System.out.println("1. visitar <URL> - Visitar una nueva página");
        System.out.println("2. atras - Ir hacia atrás");
        System.out.println("3. adelante - Ir hacia adelante");
        System.out.println("4. estado - Mostrar estado actual");
        System.out.println("5. historial - Mostrar historial completo");
        System.out.println("6. salir - Cerrar navegador");

        String comando;
        while (true) {
            System.out.print("\nNavegador> ");
            comando = scanner.nextLine().trim().toLowerCase();

            if (comando.equals("salir") || comando.equals("exit")) {
                System.out.println("Cerrando navegador...");
                break;
            } else if (comando.startsWith("visitar ")) {
                String url = comando.substring(8).trim();
                navegador.visitarPagina(url);
            } else if (comando.equals("atras")) {
                navegador.irAtras();
            } else if (comando.equals("adelante")) {
                navegador.irAdelante();
            } else if (comando.equals("estado")) {
                navegador.mostrarEstado();
            } else if (comando.equals("historial")) {
                navegador.mostrarHistorialCompleto();
            } else {
                System.out.println("Comando no reconocido. Usa: visitar <URL>, atras, adelante, estado, historial, o salir");
            }
        }

        scanner.close();
    }
}
