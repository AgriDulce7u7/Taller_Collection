package Ejercicio8;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.TreeSet;
import java.util.SortedSet;

/**
 * Clase principal que maneja la agenda de reuniones usando TreeSet.
 *
 * TreeSet es perfecto para este caso porque:
 * 1. Mantiene automáticamente los elementos ordenados
 * 2. No permite duplicados
 * 3. Ofrece operaciones eficientes para rangos
 */
public class AgendaReuniones {
    // TreeSet que mantendrá las reuniones ordenadas cronológicamente
    private TreeSet<Reunion> reuniones;

    // Constructor que inicializa el TreeSet vacío
    public AgendaReuniones() {
        this.reuniones = new TreeSet<>();
    }

    /**
     * Agrega una nueva reunión a la agenda.
     * El TreeSet se encarga automáticamente de mantener el orden.
     */
    public void agregarReunion(Reunion reunion) {
        reuniones.add(reunion);
        System.out.println("Reunión agregada: " + reunion);
    }

    /**
     * Método de conveniencia para agregar reunión con parámetros separados.
     */
    public void agregarReunion(LocalDateTime fechaHora, String asunto) {
        agregarReunion(new Reunion(fechaHora, asunto));
    }

    /**
     * Obtiene la primera reunión (la más temprana cronológicamente).
     * Usa el método first() de TreeSet que es muy eficiente.
     */
    public Reunion obtenerPrimeraReunion() {
        if (reuniones.isEmpty()) {
            System.out.println("No hay reuniones programadas.");
            return null;
        }
        return reuniones.first();
    }

    /**
     * Obtiene la última reunión (la más tardía cronológicamente).
     * Usa el método last() de TreeSet que es muy eficiente.
     */
    public Reunion obtenerUltimaReunion() {
        if (reuniones.isEmpty()) {
            System.out.println("No hay reuniones programadas.");
            return null;
        }
        return reuniones.last();
    }

    /**
     * Obtiene todas las reuniones desde hoy hasta el fin del mes actual.
     *
     * Utiliza subSet() de TreeSet para obtener eficientemente un rango.
     * Este método demuestra la potencia de TreeSet para operaciones de rango.
     */
    public SortedSet<Reunion> obtenerReunionesHastaFinDeMes() {
        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioHoy = hoy.atStartOfDay();

        // Calculamos el último día del mes actual
        LocalDate finMes = hoy.withDayOfMonth(hoy.lengthOfMonth());
        LocalDateTime finDelMes = finMes.atTime(23, 59, 59);

        // Creamos reuniones ficticias para usar como límites en subSet
        Reunion reunionInicio = new Reunion(inicioHoy, "");
        Reunion reunionFin = new Reunion(finDelMes, "");

        // subSet retorna un SortedSet con las reuniones en el rango especificado
        return reuniones.subSet(reunionInicio, true, reunionFin, true);
    }

    /**
     * Método más general para obtener reuniones en cualquier rango de fechas.
     */
    public SortedSet<Reunion> obtenerReunionesEnRango(LocalDateTime inicio, LocalDateTime fin) {
        Reunion reunionInicio = new Reunion(inicio, "");
        Reunion reunionFin = new Reunion(fin, "");
        return reuniones.subSet(reunionInicio, true, reunionFin, true);
    }

    /**
     * Muestra todas las reuniones en orden cronológico.
     * Como usamos TreeSet, el orden está garantizado.
     */
    public void mostrarTodasLasReuniones() {
        if (reuniones.isEmpty()) {
            System.out.println("No hay reuniones programadas.");
            return;
        }

        System.out.println("\n=== AGENDA DE REUNIONES (Orden Cronológico) ===");
        for (Reunion reunion : reuniones) {
            System.out.println(reunion);
        }
    }

    /**
     * Muestra reuniones en un rango específico con título descriptivo.
     */
    public void mostrarReunionesEnRango(String titulo, SortedSet<Reunion> reunionesRango) {
        System.out.println("\n=== " + titulo + " ===");
        if (reunionesRango.isEmpty()) {
            System.out.println("No hay reuniones en este período.");
        } else {
            for (Reunion reunion : reunionesRango) {
                System.out.println(reunion);
            }
        }
    }

    /**
     * Método principal que demuestra todas las funcionalidades de la agenda.
     */
    public static void main(String[] args) {
        // Creamos una nueva instancia de la agenda
        AgendaReuniones agenda = new AgendaReuniones();

        // Agregamos varias reuniones para demostrar la funcionalidad
        System.out.println("=== AGREGANDO REUNIONES ===");

        // Reuniones del mes actual (algunas en el pasado, algunas en el futuro)
        agenda.agregarReunion(
                LocalDateTime.of(2025, 9, 10, 14, 30),
                "Revisión de presupuesto Q3"
        );

        agenda.agregarReunion(
                LocalDateTime.of(2025, 9, 8, 9, 0),
                "Reunión de equipo semanal"
        );

        agenda.agregarReunion(
                LocalDateTime.of(2025, 9, 15, 16, 0),
                "Presentación de proyecto"
        );

        agenda.agregarReunion(
                LocalDateTime.of(2025, 9, 20, 11, 30),
                "Entrevistas candidatos"
        );

        // Reunión del próximo mes para mostrar el filtro
        agenda.agregarReunion(
                LocalDateTime.of(2025, 10, 5, 10, 0),
                "Planificación Q4"
        );

        // Reunión de ayer para demostrar el orden
        agenda.agregarReunion(
                LocalDateTime.of(2025, 9, 4, 15, 0),
                "Revisión semanal anterior"
        );

        // Mostramos todas las reuniones (deberían estar ordenadas automáticamente)
        agenda.mostrarTodasLasReuniones();

        // Demostramos obtener primera y última reunión
        System.out.println("\n=== PRIMERA Y ÚLTIMA REUNIÓN ===");
        Reunion primera = agenda.obtenerPrimeraReunion();
        Reunion ultima = agenda.obtenerUltimaReunion();

        if (primera != null) {
            System.out.println("Primera reunión: " + primera);
        }
        if (ultima != null) {
            System.out.println("Última reunión: " + ultima);
        }

        // Demostramos el filtro de reuniones desde hoy hasta fin de mes
        SortedSet<Reunion> reunionesFinMes = agenda.obtenerReunionesHastaFinDeMes();
        agenda.mostrarReunionesEnRango("REUNIONES DESDE HOY HASTA FIN DE MES", reunionesFinMes);

        // Ejemplo adicional: reuniones de una semana específica
        LocalDateTime inicioSemana = LocalDateTime.of(2025, 9, 8, 0, 0);
        LocalDateTime finSemana = LocalDateTime.of(2025, 9, 14, 23, 59);
        SortedSet<Reunion> reunionesSemana = agenda.obtenerReunionesEnRango(inicioSemana, finSemana);
        agenda.mostrarReunionesEnRango("REUNIONES DE LA SEMANA 8-14 SEP", reunionesSemana);
    }
}
