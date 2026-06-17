package MatNoodlesSRL;

/**
 *
 * @author gabi
 */
public abstract class Producto {
    private final double precio;

    public Producto() {
        this(0.0);
    }

    public Producto(double precio) {
        this.precio = precio;
    }

    public abstract void ingresarCantidad();

    public abstract double getCantidad();

    public abstract double getPrecio();

    @Override
    public String toString() {
        return "Producto{" + "precio=" + precio + '}';
    }

}
