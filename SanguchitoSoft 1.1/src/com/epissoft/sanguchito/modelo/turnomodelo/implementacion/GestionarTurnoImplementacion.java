/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.turnomodelo.implementacion;

import java.util.List;
import com.epissoft.sanguchito.modelo.turnomodelo.GestionarTurno;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Turno;
import com.epissoft.sanguchito.persistencia.jpa.TurnoJpaController;

/**
 *
 * @author HP
 */
public class GestionarTurnoImplementacion implements GestionarTurno{
    @Override
    public boolean insertarTurno(Turno turno){
        TurnoJpaController turnoController = new TurnoJpaController(SManager.inicializar());
        try{
            turnoController.create(turno);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean actualizarTurno(Turno turno){
        TurnoJpaController turnoController = new TurnoJpaController(SManager.inicializar());
        Turno t = turnoController.findTurno(turno.getTurCod());
        try{
            t.setTurDes(turno.getTurDes());
            t.setTurHorIni(turno.getTurHorIni());
            t.setTurHorFin(turno.getTurHorFin());
            turnoController.edit(t);
            return true;                    
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarTurno(int codigo){
        TurnoJpaController turnoController = new TurnoJpaController(SManager.inicializar());
        Turno t = turnoController.findTurno(codigo);
        try{
            t.setTurEst(true);
            turnoController.edit(t);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarTurno(int codigo){
        TurnoJpaController turnoController = new TurnoJpaController(SManager.inicializar());
        Turno t = turnoController.findTurno(codigo);
        try{
            t.setTurEst(false);
            turnoController.edit(t);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public Turno BuscarTurno(int codigo){
        return new TurnoJpaController(SManager.inicializar()).findTurno(codigo);
    }
    @Override
    public Turno BuscarTurno(String descripcion){
        return new TurnoJpaController(SManager.inicializar()).findTurno(descripcion);
    }
    @Override
    public List<Turno> ListaTurnos(){
        return new TurnoJpaController(SManager.inicializar()).findTurnoEntities();        
    }
}
