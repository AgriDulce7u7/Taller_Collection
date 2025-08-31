//package Ejercicio1;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//// Clase Ejercicio1.Libro para representar cada libro del catálogo
//class Libro {
//    private String titulo;
//    private String autor;
//    private int año;
//    private String isbn;
//
//    public Libro(String titulo, String autor, int año, String isbn) {
//        this.titulo = titulo;
//        this.autor = autor;
//        this.año = año;
//        this.isbn = isbn;
//    }
//
//    // Getters
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public String getAutor() {
//        return autor;
//    }
//
//    public int getAño() {
//        return año;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//
//    // Setters
//    public void setTitulo(String titulo) {
//        this.titulo = titulo;
//    }
//
//    public void setAutor(String autor) {
//        this.autor = autor;
//    }
//
//    public void setAño(int año) {
//        this.año = año;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("📚 %s | Autor: %s | Año: %d | ISBN: %s",
//                titulo, autor, año, isbn);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Libro libro = (Libro) obj;
//        return Objects.equals(isbn, libro.isbn);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(isbn);
//    }
//}
//
//// Clase principal para manejar el catálogo de libros
//class CatalogoLibros {
//    private ArrayList<Libro> libros;
//
////    public CatalogoLibros() {
////        this.libros = new ArrayList<>();
////    }
//
//    // Agregar libro al catálogo
//    public boolean agregarLibro(Libro libro) {
//        // Verificar que no exista un libro con el mismo ISBN
//        if (buscarPorIsbn(libro.getIsbn()) != null) {
//            System.out.println("❌ Error: Ya existe un libro con el ISBN " + libro.getIsbn());
//            return false;
//        }
//        libros.add(libro);
//        System.out.println("✅ Ejercicio1.Libro agregado exitosamente: " + libro.getTitulo());
//        return true;
//    }
//
//    // Eliminar libro por ISBN
//    public boolean eliminarPorIsbn(String isbn) {
//        Libro libro = buscarPorIsbn(isbn);
//        if (libro != null) {
//            libros.remove(libro);
//            System.out.println("✅ Ejercicio1.Libro eliminado: " + libro.getTitulo());
//            return true;
//        } else {
//            System.out.println("❌ No se encontró ningún libro con el ISBN: " + isbn);
//            return false;
//        }
//    }
//
//    // Buscar libro por ISBN (método auxiliar)
//    private Libro buscarPorIsbn(String isbn) {
//        return libros.stream()
//                .filter(libro -> libro.getIsbn().equals(isbn))
//                .findFirst()
//                .orElse(null);
//    }
//
//    // Buscar libros por autor
//    public List<Libro> buscarPorAutor(String autor) {
//        List<Libro> librosDelAutor = libros.stream()
//                .filter(libro -> libro.getAutor().toLowerCase().contains(autor.toLowerCase()))
//                .collect(Collectors.toList());
//
//        if (librosDelAutor.isEmpty()) {
//            System.out.println("❌ No se encontraron libros del autor: " + autor);
//        } else {
//            System.out.println("📖 Libros encontrados del autor '" + autor + "':");
//        }
//
//        return librosDelAutor;
//    }
//
//    // Listar libros por año ascendente
//    public List<Libro> listarPorAñoAscendente() {
//        List<Libro> librosOrdenados = new ArrayList<>(libros);
//        librosOrdenados.sort(Comparator.comparingInt(Libro::getAño));
//
//        System.out.println("📅 Libros ordenados por año (ascendente):");
//        return librosOrdenados;
//    }
//
//    // Obtener los 5 libros más recientes
//    public List<Libro> obtener5MasRecientes() {
//        List<Libro> librosRecientes = libros.stream()
//                .sorted(Comparator.comparingInt(Libro::getAño).reversed())
//                .limit(5)
//                .collect(Collectors.toList());
//
//        System.out.println("🆕 Los 5 libros más recientes:");
//        return librosRecientes;
//    }
//
//    // Mostrar todos los libros
//    public void mostrarTodosLosLibros() {
//        if (libros.isEmpty()) {
//            System.out.println("📚 El catálogo está vacío.");
//        } else {
//            System.out.println("📚 Catálogo completo (" + libros.size() + " libros):");
//            libros.forEach(System.out::println);
//        }
//    }
//
//    // Obtener cantidad de libros
//    public int getCantidadLibros() {
//        return libros.size();
//    }
//}
//
//// Clase principal para probar el sistema
//public class Main {
//    private static CatalogoLibros catalogo = new CatalogoLibros();
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        // Agregar algunos libros de ejemplo
//        cargarLibrosDeEjemplo();
//
//        // Mostrar menú interactivo
//        mostrarMenu();
//    }
//
//    private static void cargarLibrosDeEjemplo() {
//        System.out.println("🔄 Cargando libros de ejemplo...\n");
//
//        catalogo.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, "978-84-376-0494-7"));
//        catalogo.agregarLibro(new Libro("1984", "George Orwell", 1949, "978-84-666-4784-8"));
//        catalogo.agregarLibro(new Libro("El Quijote", "Miguel de Cervantes", 1605, "978-84-376-0495-4"));
//        catalogo.agregarLibro(new Libro("Rayuela", "Julio Cortázar", 1963, "978-84-376-0496-1"));
//        catalogo.agregarLibro(new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", 1985, "978-84-376-0497-8"));
//        catalogo.agregarLibro(new Libro("La sombra del viento", "Carlos Ruiz Zafón", 2001, "978-84-9759-685-9"));
//        catalogo.agregarLibro(new Libro("Sapiens", "Yuval Noah Harari", 2011, "978-84-9992-236-4"));
//        catalogo.agregarLibro(new Libro("El principito", "Antoine de Saint-Exupéry", 1943, "978-84-376-0498-5"));
//
//        System.out.println("\n" + "=".repeat(50));
//    }
//
//    private static void mostrarMenu() {
//        while (true) {
//            System.out.println("\n📚 CATÁLOGO DE LIBROS - BIBLIOTECA DIGITAL 📚");
//            System.out.println("=".repeat(50));
//            System.out.println("1. 📖 Mostrar todos los libros");
//            System.out.println("2. ➕ Agregar libro");
//            System.out.println("3. ❌ Eliminar libro por ISBN");
//            System.out.println("4. 🔍 Buscar por autor");
//            System.out.println("5. 📅 Listar por año (ascendente)");
//            System.out.println("6. 🆕 Obtener 5 más recientes");
//            System.out.println("7. 🚪 Salir");
//            System.out.println("=".repeat(50));
//            System.out.print("Selecciona una opción: ");
//
//            try {
//                int opcion = Integer.parseInt(scanner.nextLine());
//                System.out.println();
//
//                switch (opcion) {
//                    case 1:
//                        catalogo.mostrarTodosLosLibros();
//                        break;
//                    case 2:
//                        agregarLibroInteractivo();
//                        break;
//                    case 3:
//                        eliminarLibroInteractivo();
//                        break;
//                    case 4:
//                        buscarPorAutorInteractivo();
//                        break;
//                    case 5:
//                        mostrarLibrosPorAño();
//                        break;
//                    case 6:
//                        mostrar5MasRecientes();
//                        break;
//                    case 7:
//                        System.out.println("👋 ¡Gracias por usar el catálogo de libros! ¡Hasta pronto!");
//                        return;
//                    default:
//                        System.out.println("❌ Opción inválida. Intenta de nuevo.");
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("❌ Por favor, ingresa un número válido.");
//            }
//
//            System.out.println("\nPresiona Enter para continuar...");
//            scanner.nextLine();
//        }
//    }
//
//    private static void agregarLibroInteractivo() {
//        System.out.println("➕ AGREGAR NUEVO LIBRO");
//        System.out.println("-".repeat(25));
//
//        System.out.print("Título: ");
//        String titulo = scanner.nextLine();
//
//        System.out.print("Autor: ");
//        String autor = scanner.nextLine();
//
//        System.out.print("Año: ");
//        try {
//            int año = Integer.parseInt(scanner.nextLine());
//
//            System.out.print("ISBN: ");
//            String isbn = scanner.nextLine();
//
//            Libro nuevoLibro = new Libro(titulo, autor, año, isbn);
//            catalogo.agregarLibro(nuevoLibro);
//
//        } catch (NumberFormatException e) {
//            System.out.println("❌ El año debe ser un número válido.");
//        }
//    }
//
//    private static void eliminarLibroInteractivo() {
//        System.out.println("❌ ELIMINAR LIBRO POR ISBN");
//        System.out.println("-".repeat(25));
//
//        System.out.print("Ingresa el ISBN del libro a eliminar: ");
//        String isbn = scanner.nextLine();
//        catalogo.eliminarPorIsbn(isbn);
//    }
//
//    private static void buscarPorAutorInteractivo() {
//        System.out.println("🔍 BUSCAR POR AUTOR");
//        System.out.println("-".repeat(20));
//
//        System.out.print("Ingresa el nombre del autor: ");
//        String autor = scanner.nextLine();
//
//        List<Libro> librosEncontrados = catalogo.buscarPorAutor(autor);
//        librosEncontrados.forEach(System.out::println);
//    }
//
//    private static void mostrarLibrosPorAño() {
//        List<Libro> librosOrdenados = catalogo.listarPorAñoAscendente();
//        librosOrdenados.forEach(System.out::println);
//    }
//
//    private static void mostrar5MasRecientes() {
//        List<Libro> librosRecientes = catalogo.obtener5MasRecientes();
//        librosRecientes.forEach(System.out::println);
//    }
//}