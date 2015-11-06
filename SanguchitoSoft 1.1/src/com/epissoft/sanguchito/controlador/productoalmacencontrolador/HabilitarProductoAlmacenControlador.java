/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.controlador.productoalmacencontrolador;

import com.epissoft.sanguchito.modelo.productoalmacenmodelo.implementacion.GestionarProductoAlmacenImplementacion;

/**
 *
 * @author Hisae Shizumaru
 */
public class HabilitarProductoAlmacenControlador {
    public static boolean habilitarProductoAlmacen(int codigo){
        GestionarProductoAlmacenImplementacion g=new GestionarProductoAlmacenImplementacion();
        g.habilitarProductoAlmacen(codigo);
        return true;
    }
}
