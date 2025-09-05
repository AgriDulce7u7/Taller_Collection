package Ejercicio2;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Sistema de gestión de turnos para laboratorio de computación
 * Permite manejar turnos normales y preferenciales de manera eficiente
 */

public class turnosLaboratorio {
    // LinkedList para manejar la cola de turnos
    private LinkedList<String> colaTurnos;

    // Constructor que inicializa la cola vacía
    public turnosLaboratorio() {
        this.colaTurnos = new LinkedList<>();
    }

    /**
     * Agrega un turno normal al final de la cola
     * Complejidad: O(1) - inserción al final de LinkedList
     * @param estudiante nombre del estudiante
     */
    public void agregarTurnoNormal(String estudiante) {
        colaTurnos.addLast(estudiante); // Operación eficiente al final
        System.out.println("✓ Turno normal agregado para: " + estudiante);
        System.out.println("  Posición en cola: " + colaTurnos.size());
    }

    /**
     * Agrega un turno preferencial al inicio de la cola
     * Se usa cuando el estudiante tiene equipo reservado
     * Complejidad: O(1) - inserción al inicio de LinkedList
     * @param estudiante nombre del estudiante
     */
    public void agregarTurnoPreferencial(String estudiante) {
        colaTurnos.addFirst(estudiante); // Operación eficiente al inicio
        System.out.println("⭐ Turno preferencial agregado para: " + estudiante);
        System.out.println("  Posición: PRIMERA (equipo reservado)");
    }

    /**
     * Atiende al siguiente estudiante (el primero en la cola)
     * Complejidad: O(1) - eliminación del inicio de LinkedList
     * @return nombre del estudiante atendido, null si la cola está vacía
     */
    public String atenderSiguiente() {
        if (colaTurnos.isEmpty()) {
            System.out.println("❌ No hay turnos pendientes");
            return null;
        }

        // Remueve y retorna el primer elemento
        String estudiante = colaTurnos.removeFirst(); // Operación eficiente al inicio
        System.out.println("🔔 Atendiendo a: " + estudiante);
        return estudiante;
    }

    /**
     * Muestra el próximo turno sin removerlo de la cola
     * Complejidad: O(1) - solo consulta el primer elemento
     * @return nombre del siguiente estudiante, null si la cola está vacía
     */
    public String verProximoTurno() {
        if (colaTurnos.isEmpty()) {
            return null;
        }
        return colaTurnos.peekFirst(); // Operación eficiente de consulta
    }

    /**
     * Muestra toda la cola de turnos en orden
     * Útil para que los estudiantes vean su posición
     */
    public void mostrarCola() {
        if (colaTurnos.isEmpty()) {
            System.out.println("📋 Cola vacía - No hay turnos pendientes");
            return;
        }

        System.out.println("\n📋 ESTADO ACTUAL DE LA COLA:");
        System.out.println("═══════════════════════════════");

        for (int i = 0; i < colaTurnos.size(); i++) {
            String posicion = (i == 0) ? "SIGUIENTE" : "Posición " + (i + 1);
            String indicador = (i == 0) ? "▶️ " : "   ";
            System.out.println(indicador + posicion + ": " + colaTurnos.get(i));
        }

        System.out.println("═══════════════════════════════");
        System.out.println("Total de turnos: " + colaTurnos.size());
    }

    /**
     * Verifica si la cola está vacía
     * @return true si no hay turnos pendientes
     */
    public boolean estaVacia() {
        return colaTurnos.isEmpty();
    }

    /**
     * Obtiene el número total de turnos pendientes
     * @return cantidad de estudiantes en la cola
     */
    public int getTotalTurnos() {
        return colaTurnos.size();
    }

    /**
     * Método principal con menú interactivo para probar el sistema
     */
    public static void main(String[] args) {
        turnosLaboratorio laboratorio = new turnosLaboratorio();
        Scanner scanner = new Scanner(System.in);

        System.out.println("🏫 SISTEMA DE TURNOS - LABORATORIO DE COMPUTACIÓN");
        System.out.println("==================================================");

        // Agregamos algunos datos de ejemplo para demostrar el funcionamiento
        System.out.println("\n📝 Agregando turnos de ejemplo...");
        laboratorio.agregarTurnoNormal("Ana García");
        laboratorio.agregarTurnoNormal("Carlos López");
        laboratorio.agregarTurnoPreferencial("María Rodríguez"); // Tiene equipo reservado
        laboratorio.agregarTurnoNormal("Pedro Martín");
        laboratorio.agregarTurnoPreferencial("Laura Sánchez"); // También tiene equipo reservado

        laboratorio.mostrarCola();

        // Menú interactivo
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n🔧 OPCIONES DISPONIBLES:");
            System.out.println("1. Agregar turno normal");
            System.out.println("2. Agregar turno preferencial (equipo reservado)");
            System.out.println("3. Atender siguiente estudiante");
            System.out.println("4. Ver próximo turno");
            System.out.println("5. Mostrar cola completa");
            System.out.println("6. Salir");
            System.out.print("\nSelecciona una opción (1-6): ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        System.out.print("Ingresa el nombre del estudiante: ");
                        String nombreNormal = scanner.nextLine();
                        laboratorio.agregarTurnoNormal(nombreNormal);
                        break;

                    case 2:
                        System.out.print("Ingresa el nombre del estudiante (con equipo reservado): ");
                        String nombrePreferencial = scanner.nextLine();
                        laboratorio.agregarTurnoPreferencial(nombrePreferencial);
                        break;

                    case 3:
                        laboratorio.atenderSiguiente();
                        break;

                    case 4:
                        String proximo = laboratorio.verProximoTurno();
                        if (proximo != null) {
                            System.out.println("👁️ Próximo turno: " + proximo);
                        } else {
                            System.out.println("❌ No hay turnos pendientes");
                        }
                        break;

                    case 5:
                        laboratorio.mostrarCola();
                        break;

                    case 6:
                        continuar = false;
                        System.out.println("👋 Gracias por usar el sistema de turnos");
                        break;

                    default:
                        System.out.println("❌ Opción inválida. Intenta de nuevo.");
                }

            } catch (Exception e) {
                System.out.println("❌ Error en la entrada. Intenta de nuevo.");
                scanner.nextLine(); // Limpiar buffer
            }
        }

        scanner.close();
    }
}
