
package com.epissoft.sanguchito.controlador.categoriaproductoalmacencontrolador;

import com.epissoft.sanguchito.modelo.categoriaproductoalmacenmodelo.implementacion.GestionarCategoriaProductoAlmacenImplementacion;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import java.util.List;

public class MostrarCategoriaProductoAlmacenControlador {
    public static List<CategoriaProductoAlmacen> listaCategoriaProductoAlmacen(){
        return new GestionarCategoriaProductoAlmacenImplementacion().listaCategoriaProductoAlmacen();
    }
    public static List<CategoriaProductoAlmacen> listaCategoriaProductoAlmacenInhabilitadas(){
        return new GestionarCategoriaProductoAlmacenImplementacion().listaCategoriaProductoAlmacenInhabilitado();
    }
}
