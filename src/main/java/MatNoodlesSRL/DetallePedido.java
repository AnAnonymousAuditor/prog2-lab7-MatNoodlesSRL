package MatNoodlesSRL;

/**
 *
 * @author manuel
 */
public class DetallePedido {
    private double subtotal;
    private Producto producto;

    public DetallePedido() {
        this(0.0, null);
    }

    public DetallePedido(Producto producto) {
        this(0.0, producto);
    }

    public DetallePedido(double subtotal, Producto producto) {
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + ", subtotal=" + subtotal + ", producto=" + producto + '}';
    }

    public double calcularSubtotal() {
        return calcularSubtotal(producto);
    }

    public double calcularSubtotal(Producto producto) {
        return producto.getCantidad() * producto.getPrecio();
    }
}
