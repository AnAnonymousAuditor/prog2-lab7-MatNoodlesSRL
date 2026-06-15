package MatNoodlesSRL;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private MedioVenta medioVenta;
    private List<DetallePedido> detalles;

    public Pedido() {
    }

    public Pedido(MedioVenta medioVenta) {
        this.medioVenta = medioVenta;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetallePedido detalle) {
        this.detalles.add(detalle);
    }

    public MedioVenta getMedioVenta() {
        return medioVenta;
    }

    public void setMedioVenta(MedioVenta medioVenta) {
        this.medioVenta = medioVenta;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "medioVenta=" + medioVenta +
                ", detalles=" + detalles +
                '}';
    }

}
