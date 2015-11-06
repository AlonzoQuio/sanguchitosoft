package com.epissoft.sanguchito.modelo.almacenmodelo.bean;

public class InsumoAlmacen {
    private int codInsumo;
    private String desInsumo;
    private int cantidad;
    private float precio;

    public InsumoAlmacen(int codInsumo, String desInsumo, int cantidad, float precio) {
        this.codInsumo = codInsumo;
        this.desInsumo = desInsumo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getCodInsumo() {
        return codInsumo;
    }

    public void setCodInsumo(int codInsumo) {
        this.codInsumo = codInsumo;
    }

    public String getDesInsumo() {
        return desInsumo;
    }

    public void setDesInsumo(String desInsumo) {
        this.desInsumo = desInsumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
