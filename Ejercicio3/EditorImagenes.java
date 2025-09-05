package Ejercicio3;

import java.util.Stack;
import java.util.Scanner;

/**
 * CLASE PRINCIPAL: EDITOR DE IMÁGENES
 * Esta clase mantiene el historial de operaciones usando un Stack
 * y proporciona funcionalidad de deshacer
 */
public class EditorImagenes {
    private Imagen imagen;
    private Stack<Operacion> historialOperaciones; // ¡Aquí está nuestro Stack!

    public EditorImagenes(int anchoInicial, int altoInicial) {
        this.imagen = new Imagen(anchoInicial, altoInicial);
        this.historialOperaciones = new Stack<>(); // Inicializamos el stack vacío

        System.out.println("🎨 Editor de Imágenes inicializado");
        imagen.mostrarEstado();
    }

    /**
     * Ejecuta una operación y la agrega al historial.
     * Este es el corazón del patrón Command: separamos la petición de su ejecución.
     */
    public void ejecutarOperacion(Operacion operacion) {
        // Primero ejecutamos la operación
        operacion.ejecutar(imagen);

        // Luego la agregamos al historial (push al stack)
        historialOperaciones.push(operacion);

        System.out.println("📚 Operación agregada al historial (total: " +
                historialOperaciones.size() + " operaciones)");
    }

    /**
     * Deshace la última operación ejecutada.
     * Utiliza la naturaleza LIFO del Stack: la última operación es la primera en deshacerse.
     */
    public boolean deshacer() {
        // Verificamos si hay operaciones para deshacer
        if (historialOperaciones.isEmpty()) {
            System.out.println("❌ No hay operaciones para deshacer");
            return false;
        }

        // Sacamos la última operación del stack (pop)
        Operacion ultimaOperacion = historialOperaciones.pop();

        // Deshacemos la operación
        ultimaOperacion.deshacer(imagen);

        System.out.println("📚 Operaciones restantes en historial: " +
                historialOperaciones.size());

        return true;
    }

    /**
     * Muestra el historial completo de operaciones
     */
    public void mostrarHistorial() {
        if (historialOperaciones.isEmpty()) {
            System.out.println("📋 Historial vacío");
            return;
        }

        System.out.println("\n📋 HISTORIAL DE OPERACIONES (de más reciente a más antigua):");
        System.out.println("═══════════════════════════════════════════════════════════");

        // Convertimos el stack a array para poder mostrarlo sin modificarlo
        Operacion[] operaciones = historialOperaciones.toArray(new Operacion[0]);

        // Mostramos desde la más reciente (final del array) hacia la más antigua
        for (int i = operaciones.length - 1; i >= 0; i--) {
            String indicador = (i == operaciones.length - 1) ? "🔵 ÚLTIMA → " : "⚪        ";
            System.out.println(indicador + operaciones[i].getDescripcion());
        }

        System.out.println("═══════════════════════════════════════════════════════════");
    }

    /**
     * Obtiene el estado actual de la imagen
     */
    public void mostrarEstadoImagen() {
        imagen.mostrarEstado();
    }

    /**
     * Verifica si hay operaciones que se pueden deshacer
     */
    public boolean puedeDeshacer() {
        return !historialOperaciones.isEmpty();
    }

    /**
     * Obtiene el número de operaciones en el historial
     */
    public int getNumeroOperaciones() {
        return historialOperaciones.size();
    }

    /**
     * MÉTODO PRINCIPAL CON DEMOSTRACIÓN COMPLETA
     * Aquí probamos el sistema con una secuencia de operaciones y luego deshacemos 3
     */
    public static void main(String[] args) {
        System.out.println("🎨═════════════════════════════════════════════════════════🎨");
        System.out.println("     EDITOR DE IMÁGENES CON SISTEMA DE DESHACER");
        System.out.println("🎨═════════════════════════════════════════════════════════🎨\n");

        // Creamos un editor con una imagen de 800x600 pixels
        EditorImagenes editor = new EditorImagenes(800, 600);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("FASE 1: EJECUTANDO SECUENCIA DE OPERACIONES");
        System.out.println("=".repeat(60));

        // Ejecutamos una secuencia de operaciones variadas
        editor.ejecutarOperacion(new OperacionRotar(90));
        System.out.println();

        editor.ejecutarOperacion(new OperacionBrillo(20));
        System.out.println();

        editor.ejecutarOperacion(new OperacionRecortar(400, 300));
        System.out.println();

        editor.ejecutarOperacion(new OperacionFiltro("Sepia"));
        System.out.println();

        editor.ejecutarOperacion(new OperacionRotar(180));
        System.out.println();

        editor.ejecutarOperacion(new OperacionBrillo(-10));
        System.out.println();

        editor.ejecutarOperacion(new OperacionFiltro("Blanco y Negro"));
        System.out.println();

        // Mostramos el estado después de todas las operaciones
        System.out.println("\n📊 ESTADO DESPUÉS DE TODAS LAS OPERACIONES:");
        editor.mostrarEstadoImagen();
        editor.mostrarHistorial();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("FASE 2: DESHACIENDO LAS ÚLTIMAS 3 OPERACIONES");
        System.out.println("=".repeat(60));

        // Aquí viene la parte importante: deshacer 3 operaciones
        for (int i = 1; i <= 3; i++) {
            System.out.println("\n🔄 DESHACER #" + i + ":");
            System.out.println("-".repeat(30));

            if (editor.deshacer()) {
                System.out.println("\n📊 Estado después del deshacer #" + i + ":");
                editor.mostrarEstadoImagen();
            } else {
                System.out.println("No se pudo deshacer más operaciones");
                break;
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("RESULTADO FINAL");
        System.out.println("=".repeat(60));

        System.out.println("\n📈 HISTORIAL FINAL:");
        editor.mostrarHistorial();

        System.out.println("\n🖼️ ESTADO FINAL DE LA IMAGEN:");
        editor.mostrarEstadoImagen();

        // Demostración adicional del menú interactivo
        demostracionInteractiva(editor);
    }

    /**
     * Menú interactivo para que puedas experimentar con el editor
     */
    private static void demostracionInteractiva(EditorImagenes editor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("MODO INTERACTIVO - ¡Experimenta con el editor!");
        System.out.println("=".repeat(60));

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n🛠️ OPCIONES DISPONIBLES:");
            System.out.println("1. Rotar imagen");
            System.out.println("2. Ajustar brillo");
            System.out.println("3. Recortar imagen");
            System.out.println("4. Aplicar filtro");
            System.out.println("5. Deshacer última operación");
            System.out.println("6. Ver estado actual");
            System.out.println("7. Ver historial");
            System.out.println("8. Salir");
            System.out.print("\nSelecciona una opción (1-8): ");

            try {
                int opcion = scanner.nextInt();
                System.out.println();

                switch (opcion) {
                    case 1:
                        System.out.print("¿Cuántos grados rotar? (90, 180, 270, -90, etc.): ");
                        int grados = scanner.nextInt();
                        editor.ejecutarOperacion(new OperacionRotar(grados));
                        break;

                    case 2:
                        System.out.print("Ajuste de brillo (-100 a 100): ");
                        int brillo = scanner.nextInt();
                        editor.ejecutarOperacion(new OperacionBrillo(brillo));
                        break;

                    case 3:
                        System.out.print("Nuevo ancho: ");
                        int ancho = scanner.nextInt();
                        System.out.print("Nuevo alto: ");
                        int alto = scanner.nextInt();
                        editor.ejecutarOperacion(new OperacionRecortar(ancho, alto));
                        break;

                    case 4:
                        scanner.nextLine(); // Consumir salto de línea
                        System.out.print("Nombre del filtro (ej: Sepia, Vintage, B&N): ");
                        String filtro = scanner.nextLine();
                        editor.ejecutarOperacion(new OperacionFiltro(filtro));
                        break;

                    case 5:
                        editor.deshacer();
                        break;

                    case 6:
                        editor.mostrarEstadoImagen();
                        break;

                    case 7:
                        editor.mostrarHistorial();
                        break;

                    case 8:
                        continuar = false;
                        System.out.println("👋 ¡Gracias por usar el editor!");
                        break;

                    default:
                        System.out.println("❌ Opción no válida");
                }

            } catch (Exception e) {
                System.out.println("❌ Error en la entrada. Intenta de nuevo.");
                scanner.nextLine(); // Limpiar buffer
            }
        }

        scanner.close();
    }
}
