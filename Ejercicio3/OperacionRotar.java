package Ejercicio3;

/**
 * OPERACIÓN DE ROTACIÓN
 * Rota la imagen en incrementos de 90 grados
 */
class OperacionRotar extends Operacion {
    private int gradosRotacion;
    private int anchoAnterior, altoAnterior; // Para intercambiar dimensiones si es necesario

    public OperacionRotar(int grados) {
        super("Rotar " + grados + "°");
        this.gradosRotacion = grados;
    }

    @Override
    public void ejecutar(Imagen imagen) {
        // Guardamos las dimensiones anteriores
        anchoAnterior = imagen.getAncho();
        altoAnterior = imagen.getAlto();

        // Aplicamos la rotación
        imagen.setRotacion(imagen.getRotacion() + gradosRotacion);

        // Si rotamos 90° o 270°, intercambiamos ancho y alto
        if (Math.abs(gradosRotacion) == 90 || Math.abs(gradosRotacion) == 270) {
            imagen.setAncho(altoAnterior);
            imagen.setAlto(anchoAnterior);
        }

        System.out.println("✓ Ejecutada: " + descripcion);
    }

    @Override
    public void deshacer(Imagen imagen) {
        // Deshacemos la rotación
        imagen.setRotacion(imagen.getRotacion() - gradosRotacion);

        // Restauramos las dimensiones originales
        imagen.setAncho(anchoAnterior);
        imagen.setAlto(altoAnterior);

        System.out.println("↩ Deshecha: " + descripcion);
    }
}
