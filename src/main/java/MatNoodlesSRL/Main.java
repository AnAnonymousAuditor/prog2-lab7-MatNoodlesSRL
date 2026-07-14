package MatNoodlesSRL;

import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        FabricaPastas fabrica = new FabricaPastas();
        boolean terminado = false;
        while (!terminado) {
            try {
                terminado = fabrica.menu();
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
