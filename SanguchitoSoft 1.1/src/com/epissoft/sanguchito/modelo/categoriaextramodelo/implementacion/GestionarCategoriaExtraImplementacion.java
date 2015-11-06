
package com.epissoft.sanguchito.modelo.categoriaextramodelo.implementacion;

import com.epissoft.sanguchito.modelo.categoriaextramodelo.GestionarCategoriaExtra;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.persistencia.jpa.CategoriaExtraJpaController;
import java.util.Collection;
import java.util.List;

public class GestionarCategoriaExtraImplementacion implements GestionarCategoriaExtra{

    @Override
    public List<CategoriaExtra> listaCategoriasExtraPorCategoriaProducto(int catProdCod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean quitarCategoriasExtraACategoriaProducto(int catProdCod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean agregarCategoriaProductoAExtra(CategoriaExtra a, int catProdCod) {
        CategoriaExtraJpaController catC=new CategoriaExtraJpaController(SManager.inicializar());
        CategoriaExtra categoria=catC.findCategoriaExtra(a.getCatExtCod());
        try{
            Collection<CategoriaProducto> lista=categoria.getCategoriaProductoCollection();
            lista.add(new CategoriaProducto(catProdCod));
            categoria.setCategoriaProductoCollection(lista);
            catC.edit(categoria);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean agregarCategoriaExtra(CategoriaExtra cat) {
        CategoriaExtraJpaController catC=new CategoriaExtraJpaController(SManager.inicializar());
        try{
            catC.create(cat);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean modificarCategoriaExtra(CategoriaExtra cat) {
        CategoriaExtraJpaController catC=new CategoriaExtraJpaController(SManager.inicializar());
        CategoriaExtra categoria=catC.findCategoriaExtra(cat.getCatExtCod());
        try{
            categoria.setCatExtDes(cat.getCatExtDes());
            categoria.setCatExtImg(cat.getCatExtImg());
            categoria.setCatExtNom(cat.getCatExtNom());
            catC.edit(cat);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean habilitarCategoriaExtra(int codCat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean inhabilitarCategoriaExtra(int codCat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public List<CategoriaExtra> listaCategoriaExtras(){
        return new CategoriaExtraJpaController(SManager.inicializar()).findCategoriaExtra(true);
    }
    @Override
    public List<CategoriaExtra> listaCategoriaExtrasInhabilitadas(){
        return new CategoriaExtraJpaController(SManager.inicializar()).findCategoriaExtra(false);
    }
}
