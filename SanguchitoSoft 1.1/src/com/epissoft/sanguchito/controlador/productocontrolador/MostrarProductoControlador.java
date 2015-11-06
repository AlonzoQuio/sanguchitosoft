
package com.epissoft.sanguchito.controlador.productocontrolador;


import com.epissoft.sanguchito.modelo.productomodelo.implementacion.GestionarProductosImplementacion;
import com.epissoft.sanguchito.persistencia.Producto;
import java.util.List;

public class MostrarProductoControlador {
    public static List<Producto> listaProductos(){
        GestionarProductosImplementacion gest=new GestionarProductosImplementacion();
        return gest.listaProductos();
    }
    public static List<Producto> productosPorTipo(int tipo){
        GestionarProductosImplementacion gest=new GestionarProductosImplementacion();
        return gest.buscarPorCategoria(tipo);
    }
    public static List<Producto> productosInhabilitadosPorTipo(int tipo){
        GestionarProductosImplementacion gest=new GestionarProductosImplementacion();
        return gest.buscarInhabilitadosPorCategoria(tipo);
    }
}
