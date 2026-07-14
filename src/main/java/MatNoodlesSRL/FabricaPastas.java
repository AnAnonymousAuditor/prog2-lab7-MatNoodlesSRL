package MatNoodlesSRL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

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
            case 2 -> System.out.println(buscarPedido());
            case 3 -> listarPedidos();
            case 4 -> System.out.println(eliminarPedido());
            case 5 -> exportarPedidosTxt();
            case 6 -> guardarPedidos();
            case 7 -> recuperarPedidos();
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
                System.out.println(pedido);
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

    public Pedido buscarPedido(int numero) {
        if (pedidos.isEmpty()) {
            throw new NoSuchElementException("No hay pedidos almacenados.");
        }

        for (Pedido p : pedidos) {
            if (p.getID() == numero) {
                return p;
            }
        }

        throw new NoSuchElementException("No existe un pedido con ese número.");
    }

    public Pedido buscarPedido() {
        Scanner sc = new Scanner(System.in);
        int numero;
        System.out.print("Ingrese el número de pedido a buscar: ");
        numero = sc.nextInt();
        return buscarPedido(numero);
    }

    public void listarPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    public Pedido eliminarPedido(int numero) {
        if (pedidos.isEmpty()) {
            throw new NoSuchElementException("No hay pedidos almacenados.");
        }

        Iterator<Pedido> it = pedidos.iterator();
        Pedido p;

        while (it.hasNext()) {
            p = it.next();
            if (p.getID() == numero) {
                it.remove();
                return p;
            }
        }

        throw new NoSuchElementException("No existe un pedido con ese número.");
    }

    public Pedido eliminarPedido() {
        Scanner sc = new Scanner(System.in);
        int numero;
        System.out.print("Ingrese el número de pedido a eliminar: ");
        numero = sc.nextInt();
        return eliminarPedido(numero);
    }

    public void exportarPedidosTxt() {
        String ruta = "src/main/resources/pedidos.txt";
        ruta = ruta.replace('/', File.separatorChar);
        File f = new File(ruta);

        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }

        try (FileWriter fw = new FileWriter(f); BufferedWriter bw = new BufferedWriter(fw)) {
            for (Pedido p : pedidos) {
                bw.write("""
                        Pedido: %12s%06d
                        """.formatted("", p.getID()));
                bw.write("""
                        Cliente: %11s %s
                        """.formatted(p.getCliente().getNombre(), p.getCliente().getApellido()));
                bw.write("""
                        Canal: %19s
                        """.formatted(p.getMedioVenta()));
                bw.write("""
                        Importe: %,15.2f $
                        """.formatted(p.calcularTotal()));
                bw.newLine();
            }
        } catch (IOException ioe) {
            throw new UncheckedIOException("El archivo [" + f.getPath() + "] no se pudo escribir correctamente", ioe);
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

    private void guardarPedidos(){

        try (FileOutputStream fos = new FileOutputStream("pedidos.dat");
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(pedidos);
            System.out.println("Pedidos guardados exitosamente");
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los pedidos: " + e.getMessage());
        }
    }


    private void recuperarPedidos(){

        try (FileInputStream fis = new FileInputStream("pedidos.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            this.pedidos = (List<Pedido>) ois.readObject();
            System.out.println("¡Pedidos recuperados exitosamente!");
            ois.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("No se encontro el archivo 'pedidos.dat'.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al recuperar los pedidos: " + e.getMessage());
        }
    }
}




