package Ejercicio3;

/**
 * OPERACIÓN DE FILTRO
 * Aplica diferentes filtros a la imagen
 */
class OperacionFiltro extends Operacion {
    private String nuevoFiltro;
    private String filtroAnterior; // Para deshacer

    public OperacionFiltro(String filtro) {
        super("Aplicar filtro '" + filtro + "'");
        this.nuevoFiltro = filtro;
    }

    @Override
    public void ejecutar(Imagen imagen) {
        // Guardamos el filtro actual
        filtroAnterior = imagen.getFiltro();

        // Aplicamos el nuevo filtro
        imagen.setFiltro(nuevoFiltro);

        System.out.println("✓ Ejecutada: " + descripcion);
    }

    @Override
    public void deshacer(Imagen imagen) {
        // Restauramos el filtro anterior
        imagen.setFiltro(filtroAnterior);

        System.out.println("↩ Deshecha: " + descripcion);
    }
}
