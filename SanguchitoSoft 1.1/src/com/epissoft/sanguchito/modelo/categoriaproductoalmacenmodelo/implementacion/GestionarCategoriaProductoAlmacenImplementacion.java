
package com.epissoft.sanguchito.modelo.categoriaproductoalmacenmodelo.implementacion;

import com.epissoft.sanguchito.modelo.categoriaproductoalmacenmodelo.GestionarCategoriaProductoAlmacen;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import com.epissoft.sanguchito.persistencia.jpa.CategoriaProductoAlmacenJpaController;
import java.util.List;

public class GestionarCategoriaProductoAlmacenImplementacion implements GestionarCategoriaProductoAlmacen{

    @Override
    public boolean agregarCategoriaProductoAlmacen(CategoriaProductoAlmacen c){
        CategoriaProductoAlmacenJpaController cC=new CategoriaProductoAlmacenJpaController(SManager.inicializar());
        try{
            cC.create(c);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean actualizarCategoriaProductoAlmacen(CategoriaProductoAlmacen c){
        CategoriaProductoAlmacenJpaController cC=new CategoriaProductoAlmacenJpaController(SManager.inicializar());
        CategoriaProductoAlmacen cA=cC.findCategoriaProductoAlmacen(c.getCatProdAlmCod());
        try{
            cA.setCatProdAlmDes(c.getCatProdAlmDes());
            cA.setCatProdAlmImg(c.getCatProdAlmImg());
            cC.edit(cA);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public CategoriaProductoAlmacen buscarCatergoriaProductoAlmacen(int codigo){
        return new CategoriaProductoAlmacenJpaController(SManager.inicializar()).findCategoriaProductoAlmacen(codigo);
    }
    @Override
    public CategoriaProductoAlmacen buscarCatergoriaProductoAlmacen(String detalle){
        return new CategoriaProductoAlmacenJpaController(SManager.inicializar()).findCategoriaProductoAlmacen(detalle);
    }
    @Override
    public List<CategoriaProductoAlmacen> listaCategoriaProductoAlmacen(){
        return new CategoriaProductoAlmacenJpaController(SManager.inicializar()).findCategoriaProductoAlmacen(true);
    }
    @Override
    public List<CategoriaProductoAlmacen> listaCategoriaProductoAlmacenInhabilitado(){
        return new CategoriaProductoAlmacenJpaController(SManager.inicializar()).findCategoriaProductoAlmacen(false);
    }
}
