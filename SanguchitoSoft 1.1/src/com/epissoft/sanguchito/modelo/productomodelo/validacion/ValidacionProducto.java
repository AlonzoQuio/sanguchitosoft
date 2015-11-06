
package com.epissoft.sanguchito.modelo.productomodelo.validacion;

import com.epissoft.sanguchito.modelo.categoriaproductomodelo.validacion.ValidacionTipoProducto;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.persistencia.jpa.ProductoJpaController;

public class ValidacionProducto {
    public static boolean ExisteCodigo(int codigo){
        ProductoJpaController pC=new ProductoJpaController(SManager.inicializar());
        Producto producto=pC.findProducto(codigo);
        if(producto==null){
            return false;
        }else{
            return true;
        }
    }
    public static boolean ValidarCodigo(int codigo){
        return !ExisteCodigo(codigo);
    }
    public static boolean ValidarDetalle(String detalle){
        ProductoJpaController pC=new ProductoJpaController(SManager.inicializar());
        Producto producto=pC.findProducto(detalle);
        if(producto==null){
            return true;
        }else{
            return false;
        }
    }
    public static boolean ValidarTipoProducto(int tipo){
        return ValidacionTipoProducto.ExisteCodigo(tipo);
    }
    public static boolean ValidarPrecio(float precio){
        if(precio>=0){
            return true;
        }else{
            return false;
        }
    }
}

