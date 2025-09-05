package Ejercicio3;

/**
 * OPERACIÓN DE RECORTE
 * Cambia las dimensiones de la imagen
 */
class OperacionRecortar extends Operacion {
    private int nuevoAncho, nuevoAlto;
    private int anchoAnterior, altoAnterior; // Para deshacer

    public OperacionRecortar(int nuevoAncho, int nuevoAlto) {
        super("Recortar a " + nuevoAncho + "x" + nuevoAlto);
        this.nuevoAncho = nuevoAncho;
        this.nuevoAlto = nuevoAlto;
    }

    @Override
    public void ejecutar(Imagen imagen) {
        // Guardamos las dimensiones actuales para poder deshacer
        anchoAnterior = imagen.getAncho();
        altoAnterior = imagen.getAlto();

        // Aplicamos el nuevo tamaño
        imagen.setAncho(nuevoAncho);
        imagen.setAlto(nuevoAlto);

        System.out.println("✓ Ejecutada: " + descripcion);
    }

    @Override
    public void deshacer(Imagen imagen) {
        // Restauramos las dimensiones anteriores
        imagen.setAncho(anchoAnterior);
        imagen.setAlto(altoAnterior);

        System.out.println("↩ Deshecha: " + descripcion);
    }
}
