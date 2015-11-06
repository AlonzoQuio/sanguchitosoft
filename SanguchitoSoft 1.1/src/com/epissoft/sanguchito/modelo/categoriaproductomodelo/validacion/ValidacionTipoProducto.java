/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.modelo.categoriaproductomodelo.validacion;

import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.persistencia.jpa.CategoriaProductoJpaController;

public class ValidacionTipoProducto {
    public static boolean ExisteCodigo(int codigo){
        CategoriaProductoJpaController tPController=new CategoriaProductoJpaController(SManager.inicializar());
        CategoriaProducto tProducto=tPController.findCategoriaProducto(codigo);
        if(tProducto==null){
            return false;
        }else{
            return true;
        }
    }
    public static boolean ValidarCodigo(int codigo){
        return !ExisteCodigo(codigo);
    }
    public static boolean ValidarDetalle(String detalle){
        CategoriaProductoJpaController pC=new CategoriaProductoJpaController(SManager.inicializar());
        CategoriaProducto tProducto=pC.findCategoriaProducto(detalle);
        if(tProducto==null){
            return true;
        }else{
            return false;
        }
    }
}
