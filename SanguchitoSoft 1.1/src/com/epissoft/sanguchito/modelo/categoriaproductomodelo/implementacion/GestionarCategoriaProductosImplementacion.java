package com.epissoft.sanguchito.modelo.categoriaproductomodelo.implementacion;

import com.epissoft.sanguchito.modelo.categoriaproductomodelo.GestionarCategoriaProductos;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.persistencia.jpa.CategoriaProductoJpaController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestionarCategoriaProductosImplementacion implements GestionarCategoriaProductos{

    @Override
    public boolean insertarCategoriaProducto(CategoriaProducto tipoP) {
        CategoriaProductoJpaController tipoPController=new CategoriaProductoJpaController(SManager.inicializar());
        try{
            tipoPController.create(tipoP);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean actualizarCategoriaProducto(CategoriaProducto tipoP) {
        CategoriaProductoJpaController tipoPController=new CategoriaProductoJpaController(SManager.inicializar());
        CategoriaProducto tP=tipoPController.findCategoriaProducto(tipoP.getCatProdCod());
        try{
            tP.setCatProdDes(tipoP.getCatProdDes());
            tP.setCatProdImg(tipoP.getCatProdImg());
            tipoPController.edit(tP);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean inhabilitarCategoriaProducto(int codigo) {
        CategoriaProductoJpaController tipoPController=new CategoriaProductoJpaController(SManager.inicializar());
        CategoriaProducto tP=tipoPController.findCategoriaProducto(codigo);
        try{
            tP.setCatProdEst(false);
            tipoPController.edit(tP);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarCategoriaProducto(int codigo) {
        CategoriaProductoJpaController tipoPController=new CategoriaProductoJpaController(SManager.inicializar());
        CategoriaProducto tP=tipoPController.findCategoriaProducto(codigo);
        try{
            tP.setCatProdEst(true);
            tipoPController.edit(tP);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public CategoriaProducto buscarCategoriaProducto(int codigo) {
        return new CategoriaProductoJpaController(SManager.inicializar()).findCategoriaProducto(codigo);
    }

    @Override
    public CategoriaProducto buscarCategoriaProducto(String descripcion) {
        return new CategoriaProductoJpaController(SManager.inicializar()).findCategoriaProducto(descripcion);
    }
    @Override
    public List<CategoriaProducto> listaCategoriaProductos() {
        return new CategoriaProductoJpaController(SManager.inicializar()).findCategoriaProducto(true);
    }
    @Override
    public List<CategoriaProducto> listaCategoriaProductosInhabilitados(){
        return new CategoriaProductoJpaController(SManager.inicializar()).findCategoriaProducto(false);
    }
    @Override
    public List<CategoriaExtra> listaExtraPorCategoriaProducto(int codigo){
        CategoriaProductoJpaController tipoPController=new CategoriaProductoJpaController(SManager.inicializar());
        CategoriaProducto tP=tipoPController.findCategoriaProducto(codigo);
        Collection<CategoriaExtra> catExtra=tP.getCategoriaExtraCollection();
        List<CategoriaExtra> lista=new ArrayList();
        for(CategoriaExtra cat:catExtra){
            lista.add(cat);
        }
        return lista;
    }
    @Override
    public boolean agregarCategoriaExtraACategoriaProducto(int codigo, List<CategoriaExtra>categoria){
        CategoriaProductoJpaController tipoPController=new CategoriaProductoJpaController(SManager.inicializar());
        CategoriaProducto tP=tipoPController.findCategoriaProducto(codigo);
        Collection<CategoriaExtra> catExtra=tP.getCategoriaExtraCollection();
        catExtra.clear();
        for(CategoriaExtra cat:categoria){
            catExtra.add(cat);
        }
        try{
            tP.setCategoriaExtraCollection(catExtra);
            tipoPController.edit(tP);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
