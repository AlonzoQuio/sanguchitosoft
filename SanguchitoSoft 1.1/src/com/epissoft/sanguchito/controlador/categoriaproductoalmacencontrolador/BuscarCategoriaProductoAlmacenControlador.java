
package com.epissoft.sanguchito.controlador.categoriaproductoalmacencontrolador;

import com.epissoft.sanguchito.modelo.categoriaproductoalmacenmodelo.implementacion.GestionarCategoriaProductoAlmacenImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;

public class BuscarCategoriaProductoAlmacenControlador {

    public static CategoriaProductoAlmacen buscarCategoriaProductoAlmacen(String text) {
        return new GestionarCategoriaProductoAlmacenImplementacion().buscarCatergoriaProductoAlmacen(text);
    }
    
}
