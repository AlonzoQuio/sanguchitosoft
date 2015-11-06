/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.turnomodelo.validacion;

import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.jpa.TurnoJpaController;

/**
 *
 * @author HP
 */
public class TurnoValidacion {
     public static boolean existeDescripcion(String des){        
        if((new TurnoJpaController(SManager.inicializar()).findTurno(des)) == null){
            return false;
        }
        return true;        
    }
    public static boolean existeTurno(int cod){
        if((new TurnoJpaController(SManager.inicializar()).findTurno(cod)) == null){
            return false;
        }
        return true;        
    }
}
