package MatNoodlesSRL;

/**
 *
 * @author gabi
 */
public class Ravioles extends ProductoPorCaja {
    public static final double PRECIO = 5000;

    @Override
    public double getPrecio() {
        return PRECIO;
    }

    @Override
    public String toString() {
        return "Ravioles" + super.toString();
    }
}
