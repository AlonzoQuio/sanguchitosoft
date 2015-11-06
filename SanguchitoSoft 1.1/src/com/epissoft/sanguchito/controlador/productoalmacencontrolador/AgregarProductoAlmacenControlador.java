/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.controlador.productoalmacencontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import com.epissoft.sanguchito.modelo.almacenmodelo.implementacion.GestionarAlmacenImplementacion;
import com.epissoft.sanguchito.modelo.productoalmacenmodelo.implementacion.GestionarProductoAlmacenImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import com.epissoft.sanguchito.persistencia.UnidadMedida;

/**
 *
 * @author Hisae Shizumaru
 */
public class AgregarProductoAlmacenControlador {
    public static String agregarProductoAlmacen(String descripcion,String imagen,int categoria,int unidadMedida){
        if(!ValidacionGeneral.tamValido(descripcion)){
            return "El campo NOMBRE no puede estar vacio";
        }
        GestionarProductoAlmacenImplementacion g=new GestionarProductoAlmacenImplementacion();
        ProductoAlmacen p=new ProductoAlmacen(0, descripcion, (float)0, true, imagen);
        
        p.setCatProdAlmCod(new CategoriaProductoAlmacen(categoria));
        p.setUniMedCod(new UnidadMedida(unidadMedida));
        if(g.insertarProductoAlmacen(p)){
            new GestionarAlmacenImplementacion().agregarAlmacenProductoAlmacen(p.getProdAlmCod());
            return "CORRECTO";
        }else{
            return "El nombre de producto que desea utilizar, ya esta en uso";
        }
    }
}
