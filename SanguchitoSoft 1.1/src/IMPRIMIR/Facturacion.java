/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IMPRIMIR;

/**
 *
 * @author gigo
 */
public class Facturacion {
    private String Descripcion;
    private int Cantidad;
    private double precio;

    public Facturacion(String Descripcion, int Cantidad, double precio) {
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
        this.precio = precio;
    }

  
    
    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
