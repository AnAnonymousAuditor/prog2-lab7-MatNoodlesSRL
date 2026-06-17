package MatNoodlesSRL;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Pedido {

    private MedioVenta medioVenta;
    private Cliente cliente;
    private List<DetallePedido> detalles;

    public Pedido() {
        this(null, null);
    }

    public Pedido(Cliente cliente) {
        this(null, cliente);
    }

    public Pedido(MedioVenta medioVenta) {
        this(medioVenta, null);
    }

    public Pedido(MedioVenta medioVenta, Cliente cliente) {
        this.medioVenta = medioVenta;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }

    public double calcularTotal() {
        double x = 0.0;
        for (DetallePedido dp : detalles) {
            try {
                x += dp.calcularSubtotal();
            } catch (ArithmeticException ae) {
                throw new ArithmeticException("Problema al calcular el total: " + ae.getMessage());
            }
        }
        return x;
    }

    public void cargarDetalle() {
        Scanner sc = new Scanner(System.in);
        DetallePedido detalle;
        Producto producto = null;
        int tipo;
        try {
            System.out.print("""
                             Elija el tipo de pasta:
                             1) Agnolotis
                             2) Fideos al huevo
                             3) Ñoquis
                             4) Ravioles
                             """);
            tipo = sc.nextInt();
            sc.nextLine();

            producto = switch (tipo) {
                case 1 -> new Agnolotis();
                case 2 -> new FideosAlHuevo();
                case 3 -> new Gnoquis();
                case 4 -> new Ravioles();
                default -> throw new IllegalArgumentException("El número ingresado debe ser 1, 2, 3, o 4. Se ingresó: " + tipo);
            };

            producto.ingresarCantidad();
        } catch (InputMismatchException ime) {
            throw new InputMismatchException("El texto ingresado no es un número.");
        }
        detalle = new DetallePedido(producto);
        agregarDetalle(detalle);
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "medioVenta=" + medioVenta +
                ", detalles=" + detalles +
                '}';
    }
}
