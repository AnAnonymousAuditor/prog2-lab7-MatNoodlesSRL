package MatNoodlesSRL;

import java.io.Serializable;

/**
 *
 * @author gabi
 */
public abstract class Producto implements Serializable {
    protected double cantidad;

    public Producto() {
    }

    public double getCantidad() {
        return cantidad;
    };

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public abstract void ingresarCantidad();

    public abstract double getPrecio();
}
