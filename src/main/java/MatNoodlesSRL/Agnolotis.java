package MatNoodlesSRL;

/**
 *
 * @author gabi
 */
public class Agnolotis extends ProductoPorCaja {
    public static final double PRECIO = 4500;

    @Override
    public double getPrecio() {
        return PRECIO;
    }

    @Override
    public String toString() {
        return "Agnolotis" + super.toString();
    }

}
