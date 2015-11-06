
package com.epissoft.sanguchito.modelo.productoalmacenmodelo.implementacion;

import com.epissoft.sanguchito.modelo.productoalmacenmodelo.GestionarProductoAlmacen;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import com.epissoft.sanguchito.persistencia.jpa.ProductoAlmacenJpaController;
import java.util.List;

public class GestionarProductoAlmacenImplementacion implements GestionarProductoAlmacen{
    @Override
    public boolean insertarProductoAlmacen(ProductoAlmacen p) {
        ProductoAlmacenJpaController pC=new ProductoAlmacenJpaController(SManager.inicializar());
        try{
            pC.create(p);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean actualizarProductoAlmacen(ProductoAlmacen p) {
        ProductoAlmacenJpaController pC=new ProductoAlmacenJpaController(SManager.inicializar());
        ProductoAlmacen prod=pC.findProductoAlmacen(p.getProdAlmCod());
        try{
            prod.setProdAlmDes(p.getProdAlmDes());
            prod.setCatProdAlmCod(p.getCatProdAlmCod());
            prod.setUniMedCod(p.getUniMedCod());
            prod.setCatProdAlmCod(p.getCatProdAlmCod());
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    @Override
    public boolean habilitarProductoAlmacen(int codigo){
        ProductoAlmacenJpaController pC=new ProductoAlmacenJpaController(SManager.inicializar());
        ProductoAlmacen p=pC.findProductoAlmacen(codigo);
        try{
            p.setProdAlmEst(true);
            pC.edit(p);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarProductoAlmacen(int codigo){
        ProductoAlmacenJpaController pC=new ProductoAlmacenJpaController(SManager.inicializar());
        ProductoAlmacen p=pC.findProductoAlmacen(codigo);
        try{
            p.setProdAlmEst(false);
            pC.edit(p);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public ProductoAlmacen buscarProductoAlmacen(int codigo) {
        return new ProductoAlmacenJpaController(SManager.inicializar()).findProductoAlmacen(codigo);
    }
    @Override
    public ProductoAlmacen buscarProductoAlmacen(String nombre){
        return new ProductoAlmacenJpaController(SManager.inicializar()).findProductoAlmacen(nombre);
    }
    
    @Override
    public List<ProductoAlmacen> buscarPorCategoria(int categoria) {
        return new ProductoAlmacenJpaController(SManager.inicializar()).findProductoAlmacenByCategoria(categoria,true);
    }

    @Override
    public List<ProductoAlmacen> buscarInhabilitadosPorCategoria(int categoria){
        return new ProductoAlmacenJpaController(SManager.inicializar()).findProductoAlmacenByCategoria(categoria,false);
    }
    @Override
    public List<ProductoAlmacen> listaProductoAlmacen() {
        return new ProductoAlmacenJpaController(SManager.inicializar()).findProductoAlmacen(true);
    }
    @Override
    public List<ProductoAlmacen> listaProductoAlmacenInhabilitados() {
        return new ProductoAlmacenJpaController(SManager.inicializar()).findProductoAlmacen(false);
    }
}
