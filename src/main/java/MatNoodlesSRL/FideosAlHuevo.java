package MatNoodlesSRL;

/**
 *
 * @author gabi
 */
public class FideosAlHuevo extends ProductoPorPeso {
    public static final double PRECIO = 2500;

    @Override
    public double getPrecio() {
        return PRECIO;
    }

    @Override
    public String toString() {
        return "Fideos al huevo" + super.toString();
    }
}
