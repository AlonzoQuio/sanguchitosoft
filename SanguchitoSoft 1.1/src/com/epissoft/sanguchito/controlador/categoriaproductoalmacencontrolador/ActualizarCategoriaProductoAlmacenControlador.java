/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.controlador.categoriaproductoalmacencontrolador;

import com.epissoft.sanguchito.modelo.categoriaproductoalmacenmodelo.implementacion.GestionarCategoriaProductoAlmacenImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;

public class ActualizarCategoriaProductoAlmacenControlador {
    public static String actualizarCategoriaProductoAlmacen(int codigo,String detalle,String imagen){
        GestionarCategoriaProductoAlmacenImplementacion g=new GestionarCategoriaProductoAlmacenImplementacion();
        CategoriaProductoAlmacen cat=new CategoriaProductoAlmacen(codigo, detalle,imagen, true);
        if(g.actualizarCategoriaProductoAlmacen(cat)){
            return "CORRECTO";
        }else{
            return "El nombre de categoria ya esta siendo utilizado";
        }
    }
}
