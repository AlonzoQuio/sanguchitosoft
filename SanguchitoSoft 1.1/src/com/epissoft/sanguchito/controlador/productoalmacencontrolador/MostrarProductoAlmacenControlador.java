
package com.epissoft.sanguchito.controlador.productoalmacencontrolador;

import com.epissoft.sanguchito.modelo.productoalmacenmodelo.implementacion.GestionarProductoAlmacenImplementacion;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import java.util.List;

public class MostrarProductoAlmacenControlador {
    public static List<ProductoAlmacen> listaProductoAlmacen(){
        return new GestionarProductoAlmacenImplementacion().listaProductoAlmacen();
    }
    public static List<ProductoAlmacen> listaProductoAlmacen(int codTipo){
        return new GestionarProductoAlmacenImplementacion().buscarPorCategoria(codTipo);
    }
    public static List<ProductoAlmacen> listaProductoAlmacenInhabilitados(int codTipo){
        return new GestionarProductoAlmacenImplementacion().buscarInhabilitadosPorCategoria(codTipo);
    }
}
