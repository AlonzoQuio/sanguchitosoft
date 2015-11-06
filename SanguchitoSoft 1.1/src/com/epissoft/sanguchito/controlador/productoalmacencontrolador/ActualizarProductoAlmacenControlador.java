package com.epissoft.sanguchito.controlador.productoalmacencontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import com.epissoft.sanguchito.modelo.productoalmacenmodelo.implementacion.GestionarProductoAlmacenImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import com.epissoft.sanguchito.persistencia.UnidadMedida;

public class ActualizarProductoAlmacenControlador {
    public static String actualizarProductoAlmacen(int codProd,String descripcion,String imagen,int categoria,int unidadMedida){
        if(!ValidacionGeneral.tamValido(descripcion)){
            return "El campo NOMBRE no puede estar vacio";
        }
        GestionarProductoAlmacenImplementacion g=new GestionarProductoAlmacenImplementacion();
        ProductoAlmacen p=new ProductoAlmacen(codProd, descripcion, (float)0, true, imagen);
        
        p.setCatProdAlmCod(new CategoriaProductoAlmacen(categoria));
        p.setUniMedCod(new UnidadMedida(unidadMedida));
        if(g.actualizarProductoAlmacen(p)){
            return "CORRECTO";
        }else{
            return "El nombre de producto que desea utilizar, ya esta en uso";
        }
    }
}
