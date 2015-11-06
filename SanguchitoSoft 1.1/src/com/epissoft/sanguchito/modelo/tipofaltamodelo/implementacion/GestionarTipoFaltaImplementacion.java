/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.tipofaltamodelo.implementacion;

import java.util.List;
import com.epissoft.sanguchito.modelo.tipofaltamodelo.GestionarTipoFalta;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.TipoFalta;
import com.epissoft.sanguchito.persistencia.jpa.TipoFaltaJpaController;

/**
 *
 * @author HP
 */
public class GestionarTipoFaltaImplementacion implements GestionarTipoFalta{
    @Override
    public boolean insertarTipoFalta(TipoFalta tipoF){
        TipoFaltaJpaController tipoFController = new TipoFaltaJpaController(SManager.inicializar());
        try{
            tipoFController.create(tipoF);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean actualizarTipoFalta(TipoFalta tipoF){
        TipoFaltaJpaController tipoFController = new TipoFaltaJpaController(SManager.inicializar());
        TipoFalta tF = tipoFController.findTipoFalta(tipoF.getTipFalCod());
        try{
            tF.setTipFalDes(tipoF.getTipFalDes());            
            tipoFController.edit(tF);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarTipoFalta(int codigo){
        TipoFaltaJpaController tipoFController = new TipoFaltaJpaController(SManager.inicializar());
        TipoFalta tF = tipoFController.findTipoFalta(codigo);
        try{
            tF.setTipFalEst(true);
            tipoFController.edit(tF);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarTipoFalta(int codigo){
        TipoFaltaJpaController tipoFController = new TipoFaltaJpaController(SManager.inicializar());
        TipoFalta tF = tipoFController.findTipoFalta(codigo);
        try{
            tF.setTipFalEst(false);
            tipoFController.edit(tF);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public TipoFalta buscarTipoFalta(int codigo){
        return new TipoFaltaJpaController(SManager.inicializar()).findTipoFalta(codigo);
    }
    @Override
    public TipoFalta buscarTipoFalta(String descripcion){
        return new TipoFaltaJpaController(SManager.inicializar()).findTipoFalta(descripcion);
    }
    @Override
    public List<TipoFalta> listaTipoFaltas(){
        return new TipoFaltaJpaController(SManager.inicializar()).findTipoFaltaEntities();
    }
}
