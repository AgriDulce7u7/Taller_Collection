package Ejercicio3;

/**
 * OPERACIÓN DE BRILLO
 * Ajusta el brillo de la imagen
 */
class OperacionBrillo extends Operacion {
    private int ajusteBrillo;
    private int brilloAnterior; // Para deshacer

    public OperacionBrillo(int ajuste) {
        super("Ajustar brillo " + (ajuste > 0 ? "+" : "") + ajuste + "%");
        this.ajusteBrillo = ajuste;
    }

    @Override
    public void ejecutar(Imagen imagen) {
        // Guardamos el brillo actual
        brilloAnterior = imagen.getBrillo();

        // Aplicamos el ajuste
        imagen.setBrillo(imagen.getBrillo() + ajusteBrillo);

        System.out.println("✓ Ejecutada: " + descripcion);
    }

    @Override
    public void deshacer(Imagen imagen) {
        // Restauramos el brillo anterior
        imagen.setBrillo(brilloAnterior);

        System.out.println("↩ Deshecha: " + descripcion);
    }
}

