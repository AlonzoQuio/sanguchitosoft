
package com.epissoft.sanguchito.modelo.categoriaproductomodelo;

import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import java.util.List;

public interface GestionarCategoriaProductos {
    public boolean insertarCategoriaProducto(CategoriaProducto tipoP);
    public boolean agregarCategoriaExtraACategoriaProducto(int codigo, List<CategoriaExtra>cat);
    public boolean actualizarCategoriaProducto(CategoriaProducto tipoP);
    public boolean habilitarCategoriaProducto(int codigo);
    public boolean inhabilitarCategoriaProducto(int codigo);
    public CategoriaProducto buscarCategoriaProducto(int codigo);
    public CategoriaProducto buscarCategoriaProducto(String descripcion);
    public List<CategoriaProducto> listaCategoriaProductos();
    public List<CategoriaProducto> listaCategoriaProductosInhabilitados();
    public List<CategoriaExtra> listaExtraPorCategoriaProducto(int codigo);
}
