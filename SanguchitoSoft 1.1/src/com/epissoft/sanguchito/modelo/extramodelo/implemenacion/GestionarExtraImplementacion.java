/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.extramodelo.implemenacion;

import com.epissoft.sanguchito.modelo.extramodelo.GestionarExtra;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.persistencia.jpa.ExtrasJpaController;
import java.util.List;

public class GestionarExtraImplementacion implements GestionarExtra {
    @Override
    public boolean insertarExtra(Extras extra){
     ExtrasJpaController extControllerr = new ExtrasJpaController(SManager.inicializar());
        try{
            extControllerr.create(extra);
            return true;
        }catch(Exception e){
            return false;
        }        
    }
    @Override
    public boolean actualizarExtra(Extras extra){
        ExtrasJpaController extrasController = new ExtrasJpaController(SManager.inicializar());
        Extras t = extrasController.findExtras(extra.getExtCod());
        try{
            t.setExtDes(extra.getExtDes());
            t.setExtPrecSol(extra.getExtPrecSol());
            t.setExtPrecSolCar(extra.getExtPrecSolCar());
            t.setCatExtCod(extra.getCatExtCod());
            extrasController.edit(t);
            return true;                    
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarExtra(int codigo){
        ExtrasJpaController extrasController = new ExtrasJpaController(SManager.inicializar());
        Extras t = extrasController.findExtras(codigo);
        try{
            t.setExtEst(true);            
            extrasController.edit(t);
            return true;                    
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarExtra(int codigo){
        ExtrasJpaController extrasController = new ExtrasJpaController(SManager.inicializar());
        Extras t = extrasController.findExtras(codigo);
        try{
            t.setExtEst(false);            
            extrasController.edit(t);
            return true;                    
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public Extras buscarExtras(int codigo){
        return new ExtrasJpaController(SManager.inicializar()).findExtras(codigo);
    }
    
    @Override
    public List<Extras> listaExtras(){
        return new ExtrasJpaController(SManager.inicializar()).findExtras(true);        
    }
    @Override
    public List<Extras> listaExtrasInhabilitados(){
        return new ExtrasJpaController(SManager.inicializar()).findExtras(false);
    }
    @Override
    public List<Extras> buscarExtrasPorCategoria(int codCat){
        return new ExtrasJpaController(SManager.inicializar()).findExtrasByCategoria(codCat,true);
    }
    @Override
    public List<Extras> buscarExtrasInhabilitadosPorCategoria(int codCat){
        return new ExtrasJpaController(SManager.inicializar()).findExtrasByCategoria(codCat,false);
    }
}
