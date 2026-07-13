package MatNoodlesSRL;

/**
 *
 * @author manuel
 */
public enum MedioVenta {
    TELEFONO("Teléfono"),
    SITIO_WEB("Sitio Web"),
    RED_SOCIAL("Red Social");

    private String nombre;

    private MedioVenta(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
