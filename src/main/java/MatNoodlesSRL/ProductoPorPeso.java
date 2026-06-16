package MatNoodlesSRL;

/**
 *
 * @author manuel
 */
public class ProductoPorPeso extends Producto {
    private double pesoKg;

    public ProductoPorPeso() {
    }

    public ProductoPorPeso(TipoPasta tipo, double precio) {
        super(tipo, precio);
    }

    public ProductoPorPeso(double pesoKg, TipoPasta tipo, double precio) {
        super(tipo, precio);
        this.pesoKg = pesoKg;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

}
