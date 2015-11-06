/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.cargomodelo.validacion;

import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.jpa.CargoJpaController;

/**
 *
 * @author HP
 */
public class CargoValidacion {
    public static boolean existeDescripcion(String des){        
        if((new CargoJpaController(SManager.inicializar()).findCargo(des)) == null){
            return false;
        }
        return true;        
    }
    public static boolean existeCargo(int cod){
        if((new CargoJpaController(SManager.inicializar()).findCargo(cod)) == null){
            return false;
        }
        return true;        
    }
}
