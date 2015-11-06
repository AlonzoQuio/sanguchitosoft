
package com.epissoft.sanguchito.modelo.categoriaproductoalmacenmodelo;

import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import java.util.List;

public interface GestionarCategoriaProductoAlmacen {
    public boolean agregarCategoriaProductoAlmacen(CategoriaProductoAlmacen c);
    public boolean actualizarCategoriaProductoAlmacen(CategoriaProductoAlmacen c);
    public CategoriaProductoAlmacen buscarCatergoriaProductoAlmacen(int codigo);
    public CategoriaProductoAlmacen buscarCatergoriaProductoAlmacen(String detalle);
    public List<CategoriaProductoAlmacen> listaCategoriaProductoAlmacen();
    public List<CategoriaProductoAlmacen> listaCategoriaProductoAlmacenInhabilitado();
}
