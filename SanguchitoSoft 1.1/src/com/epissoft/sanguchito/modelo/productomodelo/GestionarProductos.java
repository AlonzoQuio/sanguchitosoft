
package com.epissoft.sanguchito.modelo.productomodelo;

import com.epissoft.sanguchito.persistencia.Producto;
import java.util.List;


public interface GestionarProductos {
    public boolean insertarProducto(Producto p);
    public boolean actualizarProducto(Producto p);
    public boolean inhabilitarProducto(String descripcion);
    public boolean inhabilitarProducto(int codigo);
    public boolean habilitarProducto(int codigo);
    public boolean habilitarProducto(String descripcion);
    public Producto buscarProducto(int codigo);
    public Producto buscarProducto(String descripcion);
    public List<Producto> buscarPorCategoria(String categoria);
    public List<Producto> buscarPorCategoria(int categoria);
    public List<Producto> buscarInhabilitadosPorCategoria(int categoria);
    public List<Producto> listaProductos();
    public List<Producto> listaProductosInhabilitados();
}
