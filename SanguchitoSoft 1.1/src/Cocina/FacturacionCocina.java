/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cocina;

/**
 *
 * @author JMJ
 */
public class FacturacionCocina {
    private String Descripcion;
    private String Cantidad;
    private String Modalidad;

    public FacturacionCocina(String Descripcion, String Cantidad, String Modalidad) {
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
        this.Modalidad = Modalidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getModalidad() {
        return Modalidad;
    }

    public void setModalidad(String Modalidad) {
        this.Modalidad = Modalidad;
    }

    
    
}
