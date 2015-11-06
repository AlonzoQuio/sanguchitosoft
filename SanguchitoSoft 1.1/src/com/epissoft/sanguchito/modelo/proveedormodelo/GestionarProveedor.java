/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.proveedormodelo;

import java.util.List;
import com.epissoft.sanguchito.persistencia.Proveedor;

/**
 *
 * @author HP
 */
public interface GestionarProveedor {
    public boolean insertarProveedor(Proveedor p);
    public boolean actualizarProveedor(Proveedor p);
    public boolean inhabilitarProveedor(String ruc);
    public boolean habilitarProveedor(String ruc);
    public Proveedor buscarProveedor(String ruc);
    public List<Proveedor> listaProveedors();   
}
