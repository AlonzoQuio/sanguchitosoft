/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.proveedormodelo.implementacion;

import java.util.List;
import com.epissoft.sanguchito.modelo.proveedormodelo.GestionarProveedor;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Proveedor;
import com.epissoft.sanguchito.persistencia.jpa.ProveedorJpaController;

/**
 *
 * @author HP
 */
public class GestionarProveedorImplementacion implements GestionarProveedor{
    
    @Override
    public boolean insertarProveedor(Proveedor p){
        ProveedorJpaController pC = new ProveedorJpaController(SManager.inicializar());
        try{
            pC.create(p);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean actualizarProveedor(Proveedor p){
        ProveedorJpaController pC = new ProveedorJpaController(SManager.inicializar());
        Proveedor pro = pC.findProveedor(p.getProvRuc());
        try{
            pro.setProvRazSoc(p.getProvRazSoc());
            pro.setProvNom(p.getProvNom());
            pro.setProvApePat(p.getProvApePat());
            pro.setProvApeMat(p.getProvApeMat());
            pro.setProvTel(p.getProvTel());  
            pro.setProvMail(p.getProvMail());
            pC.edit(pro);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarProveedor(String ruc){
        ProveedorJpaController pC = new ProveedorJpaController(SManager.inicializar());
        Proveedor pro = pC.findProveedor(ruc);
        try{
            pro.setProvEst(false);
            pC.edit(pro);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarProveedor(String ruc){
        ProveedorJpaController pC = new ProveedorJpaController(SManager.inicializar());
        Proveedor pro = pC.findProveedor(ruc);
        try{
            pro.setProvEst(true);
            pC.edit(pro);
            return true;
        }catch(Exception e){
            return false;
        }        
    }
    @Override
    public Proveedor buscarProveedor(String ruc){
        ProveedorJpaController pC = new ProveedorJpaController(SManager.inicializar());
        return new ProveedorJpaController(SManager.inicializar()).findProveedor(ruc);
    }
    @Override
    public List<Proveedor> listaProveedors(){
        return new ProveedorJpaController(SManager.inicializar()).findProveedor(true);
    }   
}
