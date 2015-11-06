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
public class InhabilitarProductoAlmacenControlador {
    public static boolean inhabilitarProductoAlmacen(int codigo){
        GestionarProductoAlmacenImplementacion g=new GestionarProductoAlmacenImplementacion();
        g.inhabilitarProductoAlmacen(codigo);
        return true;
    }
}
