package com.epissoft.sanguchito.modelo.productoalmacenmodelo;

import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import java.util.List;

public interface GestionarProductoAlmacen {
    public boolean insertarProductoAlmacen(ProductoAlmacen p);
    public boolean actualizarProductoAlmacen(ProductoAlmacen p);
    public boolean habilitarProductoAlmacen(int codigo);
    public boolean inhabilitarProductoAlmacen(int codigo);
    public ProductoAlmacen buscarProductoAlmacen(int codigo);
    public ProductoAlmacen buscarProductoAlmacen(String nombre);
    public List<ProductoAlmacen> buscarPorCategoria(int categoria);
    public List<ProductoAlmacen> buscarInhabilitadosPorCategoria(int categoria);
    public List<ProductoAlmacen> listaProductoAlmacen();
    public List<ProductoAlmacen> listaProductoAlmacenInhabilitados();
}
