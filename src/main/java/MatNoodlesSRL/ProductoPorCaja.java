package MatNoodlesSRL;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author manuel
 */
public abstract class ProductoPorCaja extends Producto {

    public ProductoPorCaja() {
    }

    @Override
    public void ingresarCantidad() {
        Scanner sc = new Scanner(System.in);
        double cajas;
        System.out.print("Ingrese la cantidad de cajas: ");
        try {
            cajas = sc.nextDouble();
            sc.nextLine();
            if (cajas <= 0) {
                throw new PedidoInvalidoException("La cantidad de cajas debe ser mayor a 0");
            }
            if (cajas % 1 != 0) {
                throw new PedidoInvalidoException("La cantidad de cajas debe ser un entero");
            }
            setCantidad(cajas);
        } catch (InputMismatchException ime) {
            throw new PedidoInvalidoException("El texto ingresado no es un número.");
        }
    }

    @Override
    public String toString() {
        return ", en caja";
    }
}
