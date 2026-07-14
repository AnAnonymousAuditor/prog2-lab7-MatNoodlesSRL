package MatNoodlesSRL;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class Pedido implements Serializable{

    private static int nextId = 1;
    private final int ID;
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
        this.ID = nextId++;
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

    public int getID() {
        return ID;
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
        StringBuilder ticket = new StringBuilder("""
                ============= PEDIDO #%06d =============
                Vendido por: %s
                """.formatted(getID(), getMedioVenta()));
        ticket.append("""
                Datos del cliente:
                %s
                """.formatted(getCliente()));
        ticket.append("""

                Producto                          Subtotal
                """);
        for (DetallePedido dp : getDetalles()) {
            Producto pr = dp.getProducto();
            ticket.append("""
                    %-25s      %,8.2f $
                        %.3f x %,.2f $
                    """.formatted(pr, dp.calcularSubtotal(), pr.getCantidad(), pr.getPrecio()));
        }
        ticket.append("""
                ------------------------------------------
                """);
        ticket.append("""
                TOTAL           %,24.2f $
                """.formatted(calcularTotal()));
        ticket.append("""
                ------------------------------------------
                """);
        return ticket.toString();
    }
}
