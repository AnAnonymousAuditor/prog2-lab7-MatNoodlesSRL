package MatNoodlesSRL;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner str = new Scanner(System.in);
        Scanner num = new Scanner(System.in);

        String nombre;
        String apellido;
        String email;
        String telefono;
        String direccion;

        Cliente cliente = null;
        Pedido pedido = null;

        int medio;
        boolean medioElegido = false;
        boolean terminado = false;
        boolean clienteIngresado = false;
        boolean detallesCargados = false;

        System.out.println("== Mat-Noodles SRL. ==\n");

        while (!terminado) {
            try {
                while (!clienteIngresado) {
                    System.out.println("Ingrese los datos del cliente:");

                    System.out.print("Nombre: ");
                    nombre = str.nextLine().trim();
                    if (nombre.isBlank()) {
                        throw new IllegalArgumentException("El nombre no puede estar vacío");
                    } else if (nombre.matches(".*\\d.*")){
                        throw new IllegalArgumentException("No se permiten números en el nombre");
                    }

                    System.out.print("Apellido: ");
                    apellido = str.nextLine().trim();
                    if (apellido.isBlank()) {
                        throw new IllegalArgumentException("El apellido no puede estar vacío");
                    } else if (apellido.matches(".*\\d.*")) {
                        throw new IllegalArgumentException("No se permiten números en el apellido");
                    }

                    System.out.print("E-mail: ");
                    email = str.nextLine();
                    System.out.print("Teléfono: ");
                    telefono = str.nextLine();
                    System.out.print("Dirección: ");
                    direccion = str.nextLine();

                    cliente = new Cliente(nombre, apellido, email, telefono, direccion);
                    clienteIngresado = true;
                }
                pedido = new Pedido(cliente);
                while (!medioElegido) {
                    System.out.print("""
                                     Elija el medio de venta:
                                     1) Teléfono
                                     2) Sitio web
                                     3) Red social
                                     """);
                    try {
                        medio = num.nextInt();
                    }
                    catch (InputMismatchException ime) {
                        num.nextLine();
                        throw new InputMismatchException("El texto ingresado no es un número.");
                    }
                    switch (medio) {
                        case 1 -> pedido.setMedioVenta(MedioVenta.TELEFONO);
                        case 2 -> pedido.setMedioVenta(MedioVenta.WEB);
                        case 3 -> pedido.setMedioVenta(MedioVenta.RED_SOCIAL);
                        default -> throw new IllegalArgumentException("El número ingresado debe ser 1, 2, o 3. Se ingresó: " + medio);
                    }
                    medioElegido = true;
                }
                while (!detallesCargados) {
                    pedido.cargarDetalle();
                    System.out.println("Desea cargar otro producto? (s/n)");
                    detallesCargados = str.nextLine().charAt(0) == 'n';
                }

                System.out.println("Seguir? (s/n)");
                terminado = str.nextLine().charAt(0) == 'n';
            } catch (IllegalArgumentException iae) {
                EntradaSalida.imprimir("Valor inválido: " + iae.getMessage());
            } catch (InputMismatchException ime) {
                EntradaSalida.imprimir("Error de entrada: " + ime.getMessage());
            } catch (PedidoInvalidoException pie) {
                EntradaSalida.imprimir("Pedido inválido: " + pie.getMessage());
            } catch (Exception e) {
                EntradaSalida.imprimir("ERROR: " + e.getMessage());
            }
        }
    }
}
