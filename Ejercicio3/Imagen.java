package Ejercicio3;

/**
 * CLASE IMAGEN
 * Representa el estado actual de la imagen que estamos editando.
 * Incluye propiedades como rotación, dimensiones, brillo, etc.
 */
class Imagen {
    private int ancho;
    private int alto;
    private int rotacion; // en grados (0, 90, 180, 270)
    private int brillo; // escala de -100 a 100
    private String filtro; // el filtro actual aplicado

    public Imagen(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.rotacion = 0;
        this.brillo = 0;
        this.filtro = "ninguno";
    }

    // Getters y setters con validaciones
    public int getAncho() { return ancho; }
    public void setAncho(int ancho) { this.ancho = Math.max(1, ancho); }

    public int getAlto() { return alto; }
    public void setAlto(int alto) { this.alto = Math.max(1, alto); }

    public int getRotacion() { return rotacion; }
    public void setRotacion(int rotacion) {
        // Normalizar rotación entre 0 y 360 grados
        this.rotacion = ((rotacion % 360) + 360) % 360;
    }

    public int getBrillo() { return brillo; }
    public void setBrillo(int brillo) {
        // Limitar brillo entre -100 y 100
        this.brillo = Math.max(-100, Math.min(100, brillo));
    }

    public String getFiltro() { return filtro; }
    public void setFiltro(String filtro) { this.filtro = filtro; }

    /**
     * Método para mostrar el estado actual de la imagen de forma visual
     */
    public void mostrarEstado() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║       ESTADO DE LA IMAGEN       ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.printf("║ Dimensiones: %dx%d pixels      ║%n", ancho, alto);
        System.out.printf("║ Rotación: %d°                  ║%n", rotacion);
        System.out.printf("║ Brillo: %+d%%                  ║%n", brillo);
        System.out.printf("║ Filtro: %-15s        ║%n", filtro);
        System.out.println("╚════════════════════════════════╝");
    }
}
