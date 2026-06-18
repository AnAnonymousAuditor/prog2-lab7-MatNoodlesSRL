package MatNoodlesSRL;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Cliente cliente = null;
        String nombre;
        String apellido;
        String email;
        String telefono;
        String direccion;

        Pedido pedido = new Pedido();

        int medio;
        boolean terminado = false;
        boolean medioElegido = false;
        boolean clienteIngresado = false;
        boolean detallesCargados = false;

        System.out.println("=====================\n");
        System.out.println("== Mat-Noodles SRL ==\n");
        System.out.println("=====================\n");

        while (!terminado) {
            try {
                while (!clienteIngresado) {
                    System.out.println("Ingrese los datos del cliente:");

                    System.out.print("Nombre: ");
                    nombre = sc.nextLine().trim();
                    if (nombre.isBlank()) {
                        throw new IllegalArgumentException("El nombre no puede estar vacío");
                    } else if (nombre.matches(".*\\d.*")){
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

                    cliente = new Cliente(nombre, apellido, email, telefono, direccion);
                    clienteIngresado = true;
                }
                pedido.setCliente(cliente);
                while (!medioElegido) {
                    System.out.print("""
                                     Elija el medio de venta:
                                     1) Teléfono
                                     2) Sitio web
                                     3) Red social
                                     """);
                    try {
                        medio = sc.nextInt();
                        sc.nextLine();
                    }
                    catch (InputMismatchException ime) {
                        throw new InputMismatchException("El texto ingresado no es un número.");
                    }
                    switch (medio) {
                        case 1 -> pedido.setMedioVenta(MedioVenta.TELEFONO);
                        case 2 -> pedido.setMedioVenta(MedioVenta.SITIO_WEB);
                        case 3 -> pedido.setMedioVenta(MedioVenta.RED_SOCIAL);
                        default -> throw new IllegalArgumentException("El número ingresado debe ser 1, 2, o 3. Se ingresó: " + medio);
                    }
                    medioElegido = true;
                }
                while (!detallesCargados) {
                    pedido.cargarDetalle();
                    System.out.println("Desea cargar otro producto? (s/n)");
                    detallesCargados = sc.nextLine().charAt(0) == 'n';
                }

                StringBuilder ticket = new StringBuilder("""
                    ================= PEDIDO =================
                    Vendido por %s
                    """.formatted(pedido.getMedioVenta().name().replace("_", " ")));
                ticket.append("""
                    Datos del cliente:
                    %s
                    """.formatted(pedido.getCliente()));
                ticket.append("""

                        Producto                          Subtotal
                        """);
                for (DetallePedido dp : pedido.getDetalles()) {
                    Producto p = dp.getProducto();
                    ticket.append("""
                            %-25s       %,8.2f $
                                %.3f x %,.2f $
                            """.formatted(p, dp.calcularSubtotal(), p.getCantidad(), p.getPrecio()));

                }
                ticket.append("""
                        ------------------------------------------
                        """);
                ticket.append("""
                        TOTAL           %,24.2f $
                        """.formatted(pedido.calcularTotal()));

                System.out.println(ticket);

                System.out.println("Seguir? (s/n)");
                if (sc.nextLine().charAt(0) == 'n') {
                    terminado = true;
                } else {
                    pedido = new Pedido();
                    terminado = false;
                    medioElegido = false;
                    clienteIngresado = false;
                    detallesCargados = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println("Valor inválido: " + iae.getMessage());
            } catch (InputMismatchException ime) {
                System.out.println("Error de entrada: " + ime.getMessage());
            } catch (PedidoInvalidoException pie) {
                System.out.println("Pedido inválido: " + pie.getMessage());
            } catch (ArithmeticException ae) {
                System.out.println("Error aritmético: " + ae.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
