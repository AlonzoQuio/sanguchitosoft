
package com.epissoft.sanguchito.controlador.productocontrolador;

import com.epissoft.sanguchito.modelo.productomodelo.implementacion.GestionarProductosImplementacion;

public class InhabilitarProductoControlador {
    public static boolean inhabilitarProducto(int codigo) {
        GestionarProductosImplementacion gest = new GestionarProductosImplementacion();
        gest.inhabilitarProducto(codigo);
        return true;
    }
}
