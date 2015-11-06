
package com.epissoft.sanguchito.controlador.productoalmacencontrolador;

import com.epissoft.sanguchito.modelo.productoalmacenmodelo.implementacion.GestionarProductoAlmacenImplementacion;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import java.util.List;

public class BuscarProductoAlmacenControlador {
    public static ProductoAlmacen buscarProductoAlmacen(int cod){
        return new GestionarProductoAlmacenImplementacion().buscarProductoAlmacen(cod);
    }
    public static ProductoAlmacen buscarProductoAlmacen(String codigo){
        return new GestionarProductoAlmacenImplementacion().buscarProductoAlmacen(codigo);
    }
}
