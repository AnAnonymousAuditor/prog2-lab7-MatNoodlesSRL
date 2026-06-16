package MatNoodlesSRL;

import javax.swing.JOptionPane;

/**
 *
 * @author manuel
 */
public class EntradaSalida {

    public static String leerCadena(String txt) {
        return JOptionPane.showInputDialog(txt);
    }

    public static int leerInt(String txt) {
        String s = leerCadena(txt);
        return s.equals("") ? 0 : Integer.parseInt(s);
    }

    public static double leerDouble(String txt) {
        String s = leerCadena(txt);
        return s.equals("") ? 0 : Double.parseDouble(s);
    }

    public static char leerChar(String txt) {
        return leerCadena(txt).charAt(0);
    }

    public static void imprimir(String txt) {
        JOptionPane.showMessageDialog(null, txt);
    }
}
