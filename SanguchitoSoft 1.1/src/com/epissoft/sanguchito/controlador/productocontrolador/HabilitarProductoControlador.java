/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.controlador.productocontrolador;

import com.epissoft.sanguchito.modelo.productomodelo.implementacion.GestionarProductosImplementacion;

/**
 *
 * @author Hisae Shizumaru
 */
public class HabilitarProductoControlador {
    public static boolean habilitarProducto(int codigo){
        GestionarProductosImplementacion gest = new GestionarProductosImplementacion();
        gest.habilitarProducto(codigo);
        return true;
    }
}
