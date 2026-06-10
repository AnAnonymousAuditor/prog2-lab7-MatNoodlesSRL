package MatNoodlesSRL;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==PASTAS SANTIGO==\n");

        try {
            System.out.println("Ingrese los datos del cliente:\n");
            System.out.println("NOMBRE:");
            String nombre = sc.nextLine();
            System.out.println("APELLIDO:");
            String apellido = sc.nextLine();

            if (nombre.trim().isEmpty() || apellido.trim().isEmpty()){
                throw new IllegalArgumentException("El nombre o apellido no pueden estar vacios");
            }
            else if (nombre.trim().matches(".*\\d.*") || apellido.trim().matches(".*\\d.*")){
                throw new IllegalArgumentException("No se permiten numeros en nombre y apellido");
            }

            System.out.println("MAIL:");
            String mail = sc.nextLine();
            String tel = sc.nextLine();
            String direccion = sc.nextLine();

            System.out.println("Ingrese el medio de venta (telefono/web/RS");
            String medio = sc.nextLine();

            System.out.println("Ingrese el tipo de pasta:\n");
            System.out.println("1.fideos al huevo\n");
            System.out.println("2.raviolesn\n");
            System.out.println("3.ñoquis\n");
            System.out.println("4.agnolotis\n");
            int op = sc.nextInt();
            if (op < 1 || op > 4){
                throw new ArithmeticException("Opcion debe estar entre 1 y 4");
            }
            boolean escaja = true;
            String pasta = "";
            switch (op) {
                case 1:
                     pasta = "fideos al huevo";
                    escaja = false;
                    break;
                case 2:
                    pasta = "ravioles";
                    break;
                case 3:
                    pasta = "ñoquis";
                    escaja = false;
                    break;
                case 4:
                    pasta = "agnolotis";
                    break;
            }

            int cajas = 1;
            double kgs = 1;

            System.out.println("Ingrese cantidad de cajas (número entero mayor a 0):");
            sc.nextLine(); // Limpiamos el búfer por seguridad
            String entradaCajas = sc.nextLine().trim();

            if (!entradaCajas.matches("\\d+")) {
                throw new PedidoInvalidoException("La cantidad de cajas no puede contener letras ni signos decimales.");
            }


            cajas = Integer.parseInt(entradaCajas);


            if (cajas <= 0) {
                throw new PedidoInvalidoException("La cantidad de cajas debe ser mayor a 0.");
            }

            if (!escaja) {
                System.out.println("Ingrese cantidad de kgs (mayor a 0 y hasta 10kg):");

                try {

                    kgs = sc.nextDouble();

                    if (kgs <= 0 || kgs > 10) {
                        throw new PedidoInvalidoException("El peso debe ser mayor a 0 y no superar los 10kg.");
                    }

                } catch (InputMismatchException e) {
                    sc.nextLine(); // Limpiamos el buffer para que no quede la letra trabada
                    throw new PedidoInvalidoException("Los kgs no pueden contener letras.");
                }

                double precio = 1500.0; // Definimos un precio base ficticio por caja o por kg
                double total = 0;

                if (escaja) {
                    total = cajas * precio;
                } else {
                    total = kgs * precio;
                }

                System.out.println("\n=================================");
                System.out.println("       RESUMEN DEL PEDIDO        ");
                System.out.println("=================================");
                System.out.println("Cliente: " + nombre + " " + apellido);
                System.out.println("Contacto: " + mail + " | Tel: " + tel);
                System.out.println("Dirección: " + direccion);
                System.out.println("Medio de Origen: " + medio);
                System.out.println("---------------------------------");
                // Usamos la variable 'pasta' del switch y mostramos cajas o kgs dinámicamente
                System.out.println("Producto: " + pasta);
                if (escaja) {
                    System.out.println("Cantidad: " + cajas + " Cajas");
                } else {
                    System.out.println("Cantidad: " + kgs + " Kg");
                }
                System.out.println("---------------------------------");
                System.out.println("TOTAL A PAGAR: $" + total);
                System.out.println("=================================");
            }

        }catch (IllegalArgumentException e){
            System.out.println("ERROR EN CAMPO DE NOMBRE O APELLIDO: " + e.getMessage());
        }
        catch (ArithmeticException e){
            System.out.println("ERROR EN MENU:" +e.getMessage());
        }
        catch (InputMismatchException e){
            System.out.println("Error en input: " +e.getMessage());
        }
        catch (PedidoInvalidoException e){
            System.out.println("error en cantidades: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}