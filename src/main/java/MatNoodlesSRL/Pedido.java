package MatNoodlesSRL;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    private Cliente datoscliente;
    private String medioDeVenta; 
    private 

    public Pedido(Cliente datoscliente, String medioDeVenta) {
        this.datoscliente = datoscliente;
        this.medioDeVenta = medioDeVenta;
        this.productos = new ArrayList<>(); 
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    //GETTERS Y SETTERS
    public Cliente getDatoscliente() {
        return datoscliente;
    }

    public void setDatoscliente(Cliente datoscliente) {
        this.datoscliente = datoscliente;
    }

    public String getMedioDeVenta() {
        return medioDeVenta;
    }

    public void setMedioDeVenta(String medioDeVenta) {
        this.medioDeVenta = medioDeVenta;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // TOSTRING

    @Override
    public String toString() {
        return "Pedido{" + 
                "datoscliente=" + datoscliente +
                ", medioDeVenta=" + medioDeVenta +
                ", productos=" + productos + 
                '}';
    }
   
}
    


 

