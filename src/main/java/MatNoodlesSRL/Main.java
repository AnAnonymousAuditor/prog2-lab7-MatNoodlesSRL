package MatNoodlesSRL;

import java.io.UncheckedIOException;
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
                System.out.println("Error de ingreso de datos: " + ime.getMessage());
            } catch (ArithmeticException ae) {
                System.out.println("Error aritmético: " + ae.getMessage());
            } catch (UncheckedIOException uioe) {
                System.out.println("Error de entrada/salida: " + uioe.getMessage());

                Throwable cause = uioe.getCause();
                while (cause != null) {
                    System.out.println("Causado por: " + cause.getMessage());
                    cause = cause.getCause();
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
