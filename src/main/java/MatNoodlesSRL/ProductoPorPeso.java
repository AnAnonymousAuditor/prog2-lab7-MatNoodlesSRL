package MatNoodlesSRL;

/**
 *
 * @author manuel
 */
public class ProductoPorPeso extends Producto {
    private int pesoKg;

    public ProductoPorPeso() {
    }

    public ProductoPorPeso(int pesoKg, TipoPasta tipo, double precio) {
        super(tipo, precio);
        this.pesoKg = pesoKg;
    }

    public int getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(int pesoKg) {
        this.pesoKg = pesoKg;
    }
    
}
