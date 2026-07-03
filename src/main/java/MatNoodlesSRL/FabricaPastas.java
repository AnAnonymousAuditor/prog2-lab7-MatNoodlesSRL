package MatNoodlesSRL;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author manuel
 */
public class FabricaPastas {
    private List<Pedido> pedidos;

    public FabricaPastas() {
        this.pedidos = new ArrayList<>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // TODO: terminar menu()
    public boolean menu() {
        Scanner sc = new Scanner(System.in);
        int op;
        boolean done = false;
        System.out.print("""
                ===== MAT-NOODLES SRL =====
                1. Agregar pedido
                2. Buscar pedido
                3. Listar pedidos
                4. Eliminar pedido
                5. Exportar pedidos a TXT
                6. Guardar pedidos (Serialización)
                7. Recuperar pedidos (Deserialización)
                8. Salir
                Opción:$""".replace('$', ' '));
        op = sc.nextInt();
        switch (op) {
            case 1 -> agregarPedido();
            case 2 -> {
                int numero;
                System.out.print("Ingrese el número de pedido a buscar: ");
                numero = sc.nextInt();
                buscarPedido(numero);
            }
            case 3 -> listarPedidos();
            case 4 -> {
                int numero;
                System.out.print("Ingrese el número de pedido a eliminar: ");
                numero = sc.nextInt();
                eliminarPedido(numero);
            }
            case 5 -> throw new UnsupportedOperationException("Operación aún no soportada.");
            case 6 -> throw new UnsupportedOperationException("Operación aún no soportada.");
            case 7 -> throw new UnsupportedOperationException("Operación aún no soportada.");
            case 8 -> done = true;
            default -> throw new IllegalArgumentException("Error: el número ingresado debe estar entre 1 y 8. Se ingresó: " + op);
        }
        return done;
    }

    public void agregarPedido() {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        Pedido pedido = new Pedido();
        MedioVenta medio = null;
        boolean pedidoAgregado = false;
        boolean detallesCargados = false;

        while (!pedidoAgregado) {
            try {
                while (cliente == null) {
                    cliente = ingresarCliente();
                }
                pedido.setCliente(cliente);

                while (medio == null) {
                    medio = elegirMedio();
                }
                pedido.setMedioVenta(medio);

                while (!detallesCargados) {
                    pedido.cargarDetalle();
                    System.out.println("Desea cargar otro producto? (s/n)");
                    detallesCargados = sc.nextLine().charAt(0) == 'n';
                }

                getPedidos().add(pedido);
                imprimirPedido(pedido);
                pedidoAgregado = true;
            } catch (IllegalArgumentException iae) {
                System.out.println("Valor inválido: " + iae.getMessage());
            } catch (InputMismatchException ime) {
                System.out.println("Error de entrada: " + ime.getMessage());
            } catch (PedidoInvalidoException pie) {
                System.out.println("Pedido inválido: " + pie.getMessage());
            }
        }
    }

    public Pedido buscarPedido(int numero){
        for (Pedido p : pedidos) {
            if (p.getID() == numero) {
                return p;
            }
        }
        throw new NoSuchElementException("No existe un pedido con ese número.");
    }

    public void listarPedidos() {
        for (Pedido pedido : pedidos) {
            imprimirPedido(pedido);
        }
    }

    public void eliminarPedido(int numero) {
        Iterator<Pedido> it = pedidos.iterator();
        while (it.hasNext()) {
            Pedido p = it.next();
            if (p.getID() == numero) {
                it.remove();
            }
        }
    }

    private Cliente ingresarCliente() {
        Scanner sc = new Scanner(System.in);
        String nombre;
        String apellido;
        String email;
        String telefono;
        String direccion;

        System.out.println("Ingrese los datos del cliente:");

        System.out.print("Nombre: ");
        nombre = sc.nextLine().trim();
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        } else if (nombre.matches(".*\\d.*")) {
            throw new IllegalArgumentException("No se permiten números en el nombre");
        }

        System.out.print("Apellido: ");
        apellido = sc.nextLine().trim();
        if (apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        } else if (apellido.matches(".*\\d.*")) {
            throw new IllegalArgumentException("No se permiten números en el apellido");
        }

        System.out.print("E-mail: ");
        email = sc.nextLine();
        System.out.print("Teléfono: ");
        telefono = sc.nextLine();
        System.out.print("Dirección: ");
        direccion = sc.nextLine();

        return new Cliente(nombre, apellido, email, telefono, direccion);
    }

    private MedioVenta elegirMedio() {
        Scanner sc = new Scanner(System.in);
        int medio;

        System.out.print("""
                Elija el medio de venta:
                1) Teléfono
                2) Sitio web
                3) Red social
                """);
        try {
            medio = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException ime) {
            throw new InputMismatchException("El texto ingresado no es un número.");
        }

        return switch (medio) {
            case 1 ->
                MedioVenta.TELEFONO;
            case 2 ->
                MedioVenta.SITIO_WEB;
            case 3 ->
                MedioVenta.RED_SOCIAL;
            default ->
                throw new IllegalArgumentException("El número ingresado debe ser 1, 2, o 3. Se ingresó: " + medio);
        };
    }

    private void imprimirPedido(Pedido p) {
        StringBuilder ticket = new StringBuilder("""
                ============== PEDIDO %06d ==============
                Vendido por %s
                """.formatted(p.getID(), p.getMedioVenta().name().replace("_", " ")));
        ticket.append("""
                Datos del cliente:
                %s
                """.formatted(p.getCliente()));
        ticket.append("""

                Producto                          Subtotal
                """);
        for (DetallePedido dp : p.getDetalles()) {
            Producto pr = dp.getProducto();
            ticket.append("""
                    %-25s       %,8.2f $
                        %.3f x %,.2f $
                    """.formatted(pr, dp.calcularSubtotal(), pr.getCantidad(), pr.getPrecio()));

        }
        ticket.append("""
                ------------------------------------------
                """);
        ticket.append("""
                TOTAL           %,24.2f $
                """.formatted(p.calcularTotal()));
        System.out.println(ticket);
        System.out.println("------------------------------------------");
    }
}
