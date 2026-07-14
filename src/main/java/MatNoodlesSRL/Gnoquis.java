package MatNoodlesSRL;

/**
 *
 * @author gabi
 */
public class Gnoquis extends ProductoPorPeso {
    public static final double PRECIO = 3000;

    @Override
    public double getPrecio() {
        return PRECIO;
    }

    @Override
    public String toString() {
        return "Ñoquis" + super.toString();
    }
}
