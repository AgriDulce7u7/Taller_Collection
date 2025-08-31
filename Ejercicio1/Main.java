package Ejercicio1;

import java.util.*;

public class Main {
    private static catalogoLibros catalogo = new catalogoLibros();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Agregar algunos libros de ejemplo
        cargarLibrosDeEjemplo();

        // Mostrar menú interactivo
        mostrarMenu();
    }

    private static void cargarLibrosDeEjemplo() {
        System.out.println("🔄 Cargando libros de ejemplo...\n");

        catalogo.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, "978-84-376-0494-7"));
        catalogo.agregarLibro(new Libro("1984", "George Orwell", 1949, "978-84-666-4784-8"));
        catalogo.agregarLibro(new Libro("El Quijote", "Miguel de Cervantes", 1605, "978-84-376-0495-4"));
        catalogo.agregarLibro(new Libro("Rayuela", "Julio Cortázar", 1963, "978-84-376-0496-1"));
        catalogo.agregarLibro(new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", 1985, "978-84-376-0497-8"));
        catalogo.agregarLibro(new Libro("La sombra del viento", "Carlos Ruiz Zafón", 2001, "978-84-9759-685-9"));
        catalogo.agregarLibro(new Libro("Sapiens", "Yuval Noah Harari", 2011, "978-84-9992-236-4"));
        catalogo.agregarLibro(new Libro("El principito", "Antoine de Saint-Exupéry", 1943, "978-84-376-0498-5"));

        System.out.println("\n" + "=".repeat(50));
    }

    private static void mostrarMenu() {
        while (true) {
            System.out.println("\n📚 CATÁLOGO DE LIBROS - BIBLIOTECA DIGITAL 📚");
            System.out.println("=".repeat(50));
            System.out.println("1. 📖 Mostrar todos los libros");
            System.out.println("2. ➕ Agregar libro");
            System.out.println("3. ❌ Eliminar libro por ISBN");
            System.out.println("4. 🔍 Buscar por autor");
            System.out.println("5. 📅 Listar por año (ascendente)");
            System.out.println("6. 🆕 Obtener 5 más recientes");
            System.out.println("7. 🚪 Salir");
            System.out.println("=".repeat(50));
            System.out.print("Selecciona una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                System.out.println();

                switch (opcion) {
                    case 1:
                        catalogo.mostrarTodosLosLibros();
                        break;
                    case 2:
                        agregarLibroInteractivo();
                        break;
                    case 3:
                        eliminarLibroInteractivo();
                        break;
                    case 4:
                        buscarPorAutorInteractivo();
                        break;
                    case 5:
                        mostrarLibrosPorAño();
                        break;
                    case 6:
                        mostrar5MasRecientes();
                        break;
                    case 7:
                        System.out.println("👋 ¡Gracias por usar el catálogo de libros! ¡Hasta pronto!");
                        return;
                    default:
                        System.out.println("❌ Opción inválida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, ingresa un número válido.");
            }

            System.out.println("\nPresiona Enter para continuar...");
            scanner.nextLine();
        }
    }

    private static void agregarLibroInteractivo() {
        System.out.println("➕ AGREGAR NUEVO LIBRO");
        System.out.println("-".repeat(25));

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Año: ");
        try {
            int año = Integer.parseInt(scanner.nextLine());

            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();

            Libro nuevoLibro = new Libro(titulo, autor, año, isbn);
            catalogo.agregarLibro(nuevoLibro);

        } catch (NumberFormatException e) {
            System.out.println("❌ El año debe ser un número válido.");
        }
    }

    private static void eliminarLibroInteractivo() {
        System.out.println("❌ ELIMINAR LIBRO POR ISBN");
        System.out.println("-".repeat(25));

        System.out.print("Ingresa el ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();
        catalogo.eliminarPorIsbn(isbn);
    }

    private static void buscarPorAutorInteractivo() {
        System.out.println("🔍 BUSCAR POR AUTOR");
        System.out.println("-".repeat(20));

        System.out.print("Ingresa el nombre del autor: ");
        String autor = scanner.nextLine();

        List<Libro> librosEncontrados = catalogo.buscarPorAutor(autor);
        librosEncontrados.forEach(System.out::println);
    }

    private static void mostrarLibrosPorAño() {
        List<Libro> librosOrdenados = catalogo.listarPorAñoAscendente();
        librosOrdenados.forEach(System.out::println);
    }

    private static void mostrar5MasRecientes() {
        List<Libro> librosRecientes = catalogo.obtener5MasRecientes();
        librosRecientes.forEach(System.out::println);
    }
}
