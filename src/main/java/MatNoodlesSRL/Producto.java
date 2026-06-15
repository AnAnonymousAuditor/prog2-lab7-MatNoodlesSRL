package MatNoodlesSRL;

/**
 *
 * @author gabi
 */
public class Producto {
    private TipoPasta tipo;
    private double precio;

    public Producto() {
    }

    public Producto(TipoPasta tipo, double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    public TipoPasta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPasta tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "tipo=" + tipo + ", precio=" + precio + '}';
    }
    
}
