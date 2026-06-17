package MatNoodlesSRL;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author manuel
 */
public abstract class ProductoPorCaja extends Producto {
    private double cajas;

    public ProductoPorCaja() {
    }

    public ProductoPorCaja(double precio) {
        super(precio);
    }

    public ProductoPorCaja(int cajas, double precio) {
        super(precio);
        this.cajas = cajas;
    }

    @Override
    public void ingresarCantidad() {
        Scanner sc = new Scanner(System.in);
        double cajas;
        System.out.print("Ingrese la cantidad de cajas: ");
        try {
            cajas = sc.nextDouble();
            if (cajas <= 0) {
                throw new PedidoInvalidoException("La cantidad de cajas debe ser mayor a 0");
            }
            if (cajas % 1 != 0) {
                throw new PedidoInvalidoException("La cantidad de cajas debe ser un entero");
            }
            setCajas(cajas);
        }
        catch (InputMismatchException ime) {
            throw new PedidoInvalidoException("El texto ingresado no es un número.");
        }
    }

    @Override
    public double getCantidad() {
        return cajas;
    }

    public double getCajas() {
        return cajas;
    }

    public void setCajas(double cajas) {
        this.cajas = cajas;
    }
}
