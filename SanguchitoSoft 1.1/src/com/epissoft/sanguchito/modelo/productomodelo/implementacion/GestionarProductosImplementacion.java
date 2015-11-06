
package com.epissoft.sanguchito.modelo.productomodelo.implementacion;

import com.epissoft.sanguchito.modelo.productomodelo.GestionarProductos;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.persistencia.jpa.ProductoJpaController;
import java.util.ArrayList;
import java.util.List;

public class GestionarProductosImplementacion implements GestionarProductos{
    @Override
    public boolean insertarProducto(Producto p) {
        ProductoJpaController pC=new ProductoJpaController(SManager.inicializar());
        try{
            pC.create(p);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean actualizarProducto(Producto p) {
        ProductoJpaController pC=new ProductoJpaController(SManager.inicializar());
        Producto producto=pC.findProducto(p.getProCod());
        try{
            producto.setProDes(p.getProDes());
            producto.setProImg(p.getProImg());
            producto.setProPreSol(p.getProPreSol());
            producto.setProPreCar(p.getProPreCar());
            producto.setCatProdCod(p.getCatProdCod());
            pC.edit(producto);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarProducto(int codigo) {
        ProductoJpaController pC=new ProductoJpaController(SManager.inicializar());
        Producto producto=pC.findProducto(codigo);
        try{
            producto.setProEst(true);
            pC.edit(producto);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarProducto(String descripcion) {
        ProductoJpaController pC=new ProductoJpaController(SManager.inicializar());
        Producto producto=pC.findProducto(descripcion);
        try{
            producto.setProEst(true);
            pC.edit(producto);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarProducto(String descripcion) {
        ProductoJpaController pC=new ProductoJpaController(SManager.inicializar());
        Producto producto=pC.findProducto(descripcion);
        try{
            producto.setProEst(false);
            pC.edit(producto);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarProducto(int codigo) {
        ProductoJpaController pC=new ProductoJpaController(SManager.inicializar());
        Producto producto=pC.findProducto(codigo);
        try{
            producto.setProEst(false);
            pC.edit(producto);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public Producto buscarProducto(int codigo){
        return new ProductoJpaController(SManager.inicializar()).findProducto(codigo);
    }
    @Override
    public Producto buscarProducto(String descripcion){
        return new ProductoJpaController(SManager.inicializar()).findProducto(descripcion);
    }
    @Override
    public List<Producto> buscarPorCategoria(String categoria) {
        return new ProductoJpaController(SManager.inicializar()).findProductoEntities();
    }
    @Override
    public List<Producto> buscarPorCategoria(int categoria) {
        return new ProductoJpaController(SManager.inicializar()).findProductoByCategoria(categoria,true);
    }
    @Override
    public List<Producto> buscarInhabilitadosPorCategoria(int categoria){
        return new ProductoJpaController(SManager.inicializar()).findProductoByCategoria(categoria,false);
    }
    @Override
    public List<Producto> listaProductos() {
        return new ProductoJpaController(SManager.inicializar()).findProducto(true);
     }
    public List<Producto> listaProductosInhabilitados(){
        return new ProductoJpaController(SManager.inicializar()).findProducto(false);
    }
}
