package Ejercicio4;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

/**
 * Clase principal que gestiona la mesa de ayuda con cola de prioridad
 */
public class MesaAyudaSoporte {
    private PriorityQueue<Ticket> colaTickets;

    public MesaAyudaSoporte() {
        // La PriorityQueue usa automáticamente el compareTo del Ticket
        this.colaTickets = new PriorityQueue<>();
    }

    /**
     * Agrega un nuevo ticket a la cola de prioridad
     */
    public void recibirTicket(Ticket ticket) {
        colaTickets.offer(ticket);
        System.out.println("Ticket recibido: " + ticket);
    }

    /**
     * Atiende el siguiente ticket según la prioridad establecida
     */
    public Ticket atenderSiguiente() {
        Ticket ticket = colaTickets.poll();
        if (ticket != null) {
            System.out.println("Atendiendo ticket: " + ticket);
        } else {
            System.out.println("No hay tickets pendientes");
        }
        return ticket;
    }

    /**
     * Muestra todos los tickets pendientes en orden de prioridad
     */
    public void mostrarTicketsPendientes() {
        if (colaTickets.isEmpty()) {
            System.out.println("No hay tickets pendientes");
            return;
        }

        System.out.println("\n=== TICKETS PENDIENTES (en orden de atención) ===");
        // Creamos una copia temporal para mostrar sin modificar la cola original
        PriorityQueue<Ticket> copia = new PriorityQueue<>(colaTickets);
        int posicion = 1;
        while (!copia.isEmpty()) {
            System.out.println(posicion + ". " + copia.poll());
            posicion++;
        }
        System.out.println("===============================================\n");
    }

    public int getCantidadTicketsPendientes() {
        return colaTickets.size();
    }

    /**
     * Método principal para demostrar el funcionamiento del sistema
     */
    public static void main(String[] args) {
        MesaAyudaSoporte mesa = new MesaAyudaSoporte();

        System.out.println("=== SISTEMA DE SOPORTE TÉCNICO ===\n");

        // Simulamos la llegada de tickets con diferentes severidades y fechas
        LocalDateTime base = LocalDateTime.of(2024, 1, 15, 10, 0);

        // Creamos tickets con diferentes severidades y fechas para probar el ordenamiento
        Ticket t1 = new Ticket("T001", "Aplicación no responde", Severidad.MEDIA, base.plusMinutes(10));
        Ticket t2 = new Ticket("T002", "Servidor caído", Severidad.CRITICA, base.plusMinutes(15));
        Ticket t3 = new Ticket("T003", "Consulta sobre funcionalidad", Severidad.BAJA, base.plusMinutes(5));
        Ticket t4 = new Ticket("T004", "Error en base de datos", Severidad.ALTA, base.plusMinutes(20));
        Ticket t5 = new Ticket("T005", "Sistema lento", Severidad.MEDIA, base); // Más antiguo con severidad MEDIA
        Ticket t6 = new Ticket("T006", "Falla de seguridad", Severidad.CRITICA, base.plusMinutes(25));

        // Recibimos los tickets en orden aleatorio
        mesa.recibirTicket(t1);
        mesa.recibirTicket(t2);
        mesa.recibirTicket(t3);
        mesa.recibirTicket(t4);
        mesa.recibirTicket(t5);
        mesa.recibirTicket(t6);

        // Mostramos el orden de prioridad
        mesa.mostrarTicketsPendientes();

        // Atendemos algunos tickets para ver el comportamiento
        System.out.println("=== ATENDIENDO TICKETS ===");
        mesa.atenderSiguiente(); // Debería ser CRÍTICA más antigua
        mesa.atenderSiguiente(); // Debería ser CRÍTICA más reciente
        mesa.atenderSiguiente(); // Debería ser ALTA

        System.out.println("\nTickets restantes:");
        mesa.mostrarTicketsPendientes();

        // Atendemos el resto
        while (mesa.getCantidadTicketsPendientes() > 0) {
            mesa.atenderSiguiente();
        }
    }
}
