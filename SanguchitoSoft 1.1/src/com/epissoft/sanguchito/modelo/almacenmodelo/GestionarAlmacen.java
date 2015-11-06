/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.modelo.almacenmodelo;

import com.epissoft.sanguchito.persistencia.Almacen;
import java.util.List;

public interface GestionarAlmacen {
    public boolean agregarAlmacenProductoAlmacen(int codProducto);
    public boolean agregarAlmacenProductoAlmacen(int codProducto,int cantidadInicial);
    public boolean actualizarAlmacen(int codProducto,int nuevaCantidad);
    public List<Almacen> cantidadEnAlmacen();
    public Almacen cantidadEnAlmacen(int codProducto);
}
