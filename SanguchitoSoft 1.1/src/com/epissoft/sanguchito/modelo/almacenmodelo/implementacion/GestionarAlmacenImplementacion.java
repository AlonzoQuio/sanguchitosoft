
package com.epissoft.sanguchito.modelo.almacenmodelo.implementacion;

import com.epissoft.sanguchito.modelo.almacenmodelo.GestionarAlmacen;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Almacen;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import com.epissoft.sanguchito.persistencia.jpa.AlmacenJpaController;
import java.util.List;

public class GestionarAlmacenImplementacion implements GestionarAlmacen{

    @Override
    public boolean agregarAlmacenProductoAlmacen(int codProducto) {
        AlmacenJpaController aC=new AlmacenJpaController(SManager.inicializar());
        Almacen a=new Almacen(codProducto,0);
        a.setProductoAlmacen(new ProductoAlmacen(codProducto));
        try{
            aC.create(a);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean agregarAlmacenProductoAlmacen(int codProducto, int cantidadInicial) {
        AlmacenJpaController aC=new AlmacenJpaController(SManager.inicializar());
        Almacen a=new Almacen(codProducto,cantidadInicial);
        a.setProductoAlmacen(new ProductoAlmacen(codProducto));
        try{
            aC.create(a);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean actualizarAlmacen(int codProducto, int nuevaCantidad) {
        AlmacenJpaController aC=new AlmacenJpaController(SManager.inicializar());
        Almacen a=aC.findAlmacen(codProducto);
        try{
            a.setAlmCant(a.getAlmCant()+nuevaCantidad);
            aC.edit(a);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public List<Almacen> cantidadEnAlmacen(){
        return new AlmacenJpaController(SManager.inicializar()).findAlmacenEntities();
    }
    @Override
    public Almacen cantidadEnAlmacen(int codProducto){
        return new AlmacenJpaController(SManager.inicializar()).findAlmacen(codProducto);
    }
}
