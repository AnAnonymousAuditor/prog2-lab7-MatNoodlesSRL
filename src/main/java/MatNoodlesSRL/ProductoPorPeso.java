package MatNoodlesSRL;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author manuel
 */
public abstract class ProductoPorPeso extends Producto {
    private double pesoKg;

    public ProductoPorPeso() {
    }

    public ProductoPorPeso(double precio) {
        super(precio);
    }

    public ProductoPorPeso(double pesoKg, double precio) {
        super(precio);
        this.pesoKg = pesoKg;
    }

    @Override
    public void ingresarCantidad() {
        Scanner sc = new Scanner(System.in);
        double peso;
        System.out.print("Ingrese el peso: ");
        try {
            peso = sc.nextDouble();
            if (peso <= 0) {
                throw new PedidoInvalidoException("El peso no puede ser menor a 0kg");
            }
            if (peso > 10) {
                throw new PedidoInvalidoException("El peso no puede ser mayor a 10kg");
            }
            setPesoKg(peso);
        }
        catch (InputMismatchException ime) {
            throw new PedidoInvalidoException("El texto ingresado no es un número.");
        }
    }

    @Override
    public double getCantidad() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }
}
