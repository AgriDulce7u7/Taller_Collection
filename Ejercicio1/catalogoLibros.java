package Ejercicio1;

import java.util.*;
import java.util.stream.Collectors;

public class catalogoLibros {
    private ArrayList<Libro> libros;

    public catalogoLibros() {
        this.libros = new ArrayList<>();
    }

    // Agregar libro al catálogo
    public boolean agregarLibro(Libro libro) {
        // Verificar que no exista un libro con el mismo ISBN
        if (buscarPorIsbn(libro.getIsbn()) != null) {
            System.out.println("❌ Error: Ya existe un libro con el ISBN " + libro.getIsbn());
            return false;
        }
        libros.add(libro);
        System.out.println("✅ Ejercicio1.Libro agregado exitosamente: " + libro.getTitulo());
        return true;
    }

    // Eliminar libro por ISBN
    public boolean eliminarPorIsbn(String isbn) {
        Libro libro = buscarPorIsbn(isbn);
        if (libro != null) {
            libros.remove(libro);
            System.out.println("✅ Ejercicio1.Libro eliminado: " + libro.getTitulo());
            return true;
        } else {
            System.out.println("❌ No se encontró ningún libro con el ISBN: " + isbn);
            return false;
        }
    }

    // Buscar libro por ISBN (método auxiliar)
    private Libro buscarPorIsbn(String isbn) {
        return libros.stream()
                .filter(libro -> libro.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    // Buscar libros por autor
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> librosDelAutor = libros.stream()
                .filter(libro -> libro.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());

        if (librosDelAutor.isEmpty()) {
            System.out.println("❌ No se encontraron libros del autor: " + autor);
        } else {
            System.out.println("📖 Libros encontrados del autor '" + autor + "':");
        }

        return librosDelAutor;
    }

    // Listar libros por año ascendente
    public List<Libro> listarPorAñoAscendente() {
        List<Libro> librosOrdenados = new ArrayList<>(libros);
        librosOrdenados.sort(Comparator.comparingInt(Libro::getAño));

        System.out.println("📅 Libros ordenados por año (ascendente):");
        return librosOrdenados;
    }

    // Obtener los 5 libros más recientes
    public List<Libro> obtener5MasRecientes() {
        List<Libro> librosRecientes = libros.stream()
                .sorted(Comparator.comparingInt(Libro::getAño).reversed())
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("🆕 Los 5 libros más recientes:");
        return librosRecientes;
    }

    // Mostrar todos los libros
    public void mostrarTodosLosLibros() {
        if (libros.isEmpty()) {
            System.out.println("📚 El catálogo está vacío.");
        } else {
            System.out.println("📚 Catálogo completo (" + libros.size() + " libros):");
            libros.forEach(System.out::println);
        }
    }

    // Obtener cantidad de libros
    public int getCantidadLibros() {
        return libros.size();
    }
}
