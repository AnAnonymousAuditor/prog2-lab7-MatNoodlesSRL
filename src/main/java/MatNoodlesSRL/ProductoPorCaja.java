package MatNoodlesSRL;

/**
 *
 * @author manuel
 */
public class ProductoPorCaja extends Producto {
    private int cajas;

    public ProductoPorCaja() {
    }

    public ProductoPorCaja(TipoPasta tipo, double precio) {
        super(tipo, precio);
    }

    public ProductoPorCaja(int cajas, TipoPasta tipo, double precio) {
        super(tipo, precio);
        this.cajas = cajas;
    }

    public int getCajas() {
        return cajas;
    }

    public void setCajas(int cajas) {
        this.cajas = cajas;
    }
    
}
