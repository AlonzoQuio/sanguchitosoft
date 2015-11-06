
package com.epissoft.sanguchito.modelo.categoriaextramodelo;

import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import java.util.List;

public interface GestionarCategoriaExtra {
    public List<CategoriaExtra> listaCategoriasExtraPorCategoriaProducto(int catProdCod);
    public List<CategoriaExtra> listaCategoriaExtras();
    public List<CategoriaExtra> listaCategoriaExtrasInhabilitadas();
    public boolean quitarCategoriasExtraACategoriaProducto(int catProdCod);
    public boolean agregarCategoriaProductoAExtra(CategoriaExtra a,int catProdCod);
    public boolean agregarCategoriaExtra(CategoriaExtra cat);
    public boolean modificarCategoriaExtra(CategoriaExtra cat);
    public boolean habilitarCategoriaExtra(int codCat);
    public boolean inhabilitarCategoriaExtra(int codCat);
}
