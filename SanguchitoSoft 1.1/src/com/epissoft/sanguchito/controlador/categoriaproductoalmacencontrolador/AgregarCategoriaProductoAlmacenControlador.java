
package com.epissoft.sanguchito.controlador.categoriaproductoalmacencontrolador;

import com.epissoft.sanguchito.modelo.categoriaproductoalmacenmodelo.implementacion.GestionarCategoriaProductoAlmacenImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;

public class AgregarCategoriaProductoAlmacenControlador {
    public static String agregarCategoriaProductoAlmacen(String detalle,String imagen){
        GestionarCategoriaProductoAlmacenImplementacion g=new GestionarCategoriaProductoAlmacenImplementacion();
        CategoriaProductoAlmacen cat=new CategoriaProductoAlmacen(0, detalle,imagen, true);
        if(g.agregarCategoriaProductoAlmacen(cat)){
            return "CORRECTO";
        }else{
            return "El nombre de categoria ya esta siendo utilizado";
        }
    }
}
