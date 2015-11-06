package com.epissoft.sanguchito.controlador.productocontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import com.epissoft.sanguchito.modelo.productomodelo.implementacion.GestionarProductosImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.persistencia.Producto;

public class ActualizarProductoControlador {

    public static String actualizarProducto(int codigo, String detalle, String imagen, String precio, String precioCarta, int categoria, int codProdAlm) {
        float prec;
        float precCarta;
        try {
            prec = Float.parseFloat(precio);
            precCarta = Float.parseFloat(precioCarta);
        } catch (NumberFormatException e) {
            return "Error: Ingrese un monto valido";
        }

        if (!ValidacionGeneral.precioValido(prec)
                || !ValidacionGeneral.precioValido(precCarta)) {
            return "Error: Ingrese un monto valido";
        } else {
            Producto p = new Producto(codigo, detalle, prec, precCarta, imagen, true, codProdAlm);
            p.setCatProdCod(new CategoriaProducto(categoria));
            GestionarProductosImplementacion gest = new GestionarProductosImplementacion();
            if (gest.actualizarProducto(p)) {
                return "CORRECTO";
            } else {
                return "Error: El nombre que desea utilizar ya esta en uso";
            }
        }
    }
}
