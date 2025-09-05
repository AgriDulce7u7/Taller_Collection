package Ejercicio6;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Sistema de Control de Acceso Biométrico
 *
 * Este programa simula un sistema que registra huellas dactilares usando IDs únicos.
 * Utiliza HashSet para garantizar que no se registren huellas duplicadas.
 *
 * Conceptos clave:
 * - HashSet no permite elementos duplicados
 * - El método add() retorna false si el elemento ya existe
 * - El tamaño del conjunto no aumenta con duplicados
 */
public class ControlAccesoBiometrico {

    // HashSet para almacenar los IDs únicos de las huellas
    private HashSet<String> huellasDactilares;

    /**
     * Constructor: inicializa el sistema biométrico
     */
    public ControlAccesoBiometrico() {
        this.huellasDactilares = new HashSet<>();
        System.out.println("=== SISTEMA BIOMÉTRICO INICIALIZADO ===");
        System.out.println("Capacidad para almacenar huellas únicas\n");
    }

    /**
     * Intenta registrar una nueva huella dactilar
     * @param idHuella ID único de la huella a registrar
     * @return true si se registró exitosamente, false si ya existía
     */
    public boolean registrarHuella(String idHuella) {
        System.out.println("Intentando registrar huella con ID: " + idHuella);

        // El método add() de HashSet retorna false si el elemento ya existe
        boolean registroExitoso = huellasDactilares.add(idHuella);

        if (registroExitoso) {
            System.out.println("✅ REGISTRO EXITOSO - Nueva huella agregada");
        } else {
            System.out.println("❌ REGISTRO DENEGADO - Huella ya existe en el sistema");
            System.out.println("   Razón: No se permiten duplicados por seguridad");
        }

        mostrarEstadoSistema();
        return registroExitoso;
    }

    /**
     * Verifica si una huella ya está registrada en el sistema
     * @param idHuella ID de la huella a verificar
     * @return true si la huella existe, false si no existe
     */
    public boolean verificarHuellaExiste(String idHuella) {
        boolean existe = huellasDactilares.contains(idHuella);
        System.out.println("Verificación de huella " + idHuella + ": " +
                (existe ? "ENCONTRADA" : "NO ENCONTRADA"));
        return existe;
    }

    /**
     * Muestra el estado actual del sistema
     */
    private void mostrarEstadoSistema() {
        System.out.println("📊 Estado del sistema:");
        System.out.println("   Total de huellas registradas: " + huellasDactilares.size());
        System.out.println("   Huellas almacenadas: " + huellasDactilares);
        System.out.println("   ────────────────────────────────\n");
    }

    /**
     * Obtiene el número total de huellas únicas registradas
     * @return cantidad de huellas en el sistema
     */
    public int obtenerTotalHuellas() {
        return huellasDactilares.size();
    }

    /**
     * Método principal - Demonstración del sistema
     */
    public static void main(String[] args) {
        // Crear instancia del sistema biométrico
        ControlAccesoBiometrico sistema = new ControlAccesoBiometrico();

        System.out.println("🧪 PRUEBA 1: Registros iniciales de huellas diferentes");
        sistema.registrarHuella("HUELLA_001");
        sistema.registrarHuella("HUELLA_002");
        sistema.registrarHuella("HUELLA_003");

        System.out.println("🧪 PRUEBA 2: Intentos de registro duplicado");
        sistema.registrarHuella("HUELLA_001"); // Duplicado - debería fallar
        sistema.registrarHuella("HUELLA_002"); // Duplicado - debería fallar
        sistema.registrarHuella("HUELLA_001"); // Duplicado - debería fallar

        System.out.println("🧪 PRUEBA 3: Verificaciones de existencia");
        sistema.verificarHuellaExiste("HUELLA_001");
        sistema.verificarHuellaExiste("HUELLA_999"); // No existe

        System.out.println("🧪 PRUEBA 4: Más registros únicos");
        sistema.registrarHuella("HUELLA_004");
        sistema.registrarHuella("HUELLA_005");

        System.out.println("🧪 PRUEBA 5: Más intentos duplicados");
        sistema.registrarHuella("HUELLA_004"); // Duplicado
        sistema.registrarHuella("HUELLA_005"); // Duplicado

        // Demostración interactiva opcional
        demonstracionInteractiva(sistema);

        // Resumen final
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎯 RESUMEN FINAL DEL EXPERIMENTO:");
        System.out.println("Total de huellas únicas registradas: " + sistema.obtenerTotalHuellas());
        System.out.println("Evidencia: El tamaño del HashSet NO aumenta con duplicados");
        System.out.println("Conclusión: Sistema biométrico exitoso - sin duplicados");
        System.out.println("=".repeat(50));
    }

    /**
     * Demonstración interactiva para que el usuario pruebe el sistema
     */
    private static void demonstracionInteractiva(ControlAccesoBiometrico sistema) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n🎮 MODO INTERACTIVO (opcional)");
        System.out.println("¿Deseas probar el sistema manualmente? (s/n): ");

        if (scanner.hasNextLine()) {
            String respuesta = scanner.nextLine().toLowerCase();

            if (respuesta.equals("s") || respuesta.equals("si")) {
                System.out.println("Ingresa IDs de huellas para probar (escribe 'salir' para terminar):");

                while (true) {
                    System.out.print("ID de huella: ");
                    String input = scanner.nextLine();

                    if (input.equalsIgnoreCase("salir")) {
                        break;
                    }

                    if (!input.trim().isEmpty()) {
                        sistema.registrarHuella(input.trim());
                    }
                }
            }
        }
    }
}
