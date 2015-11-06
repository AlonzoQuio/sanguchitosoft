/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.registrofaltamodelo.implementacion;

import java.util.List;
import com.epissoft.sanguchito.modelo.registrofaltamodelo.GestionarRegistroFalta;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.RegistroFalta;
import com.epissoft.sanguchito.persistencia.jpa.RegistroFaltaJpaController;

/**
 *
 * @author HP
 */
public class GestionarRegistroFaltaImplementacion implements GestionarRegistroFalta{
    @Override
    public boolean insertarFalta(RegistroFalta rf){
        RegistroFaltaJpaController rFC = new RegistroFaltaJpaController(SManager.inicializar());
        try{
            rFC.create(rf);
            return true;
        }catch(Exception e){
            return false;
        }
    }
//    @Override
//    public RegistroFalta buscarFalta(RegistroFaltaPK k){
//        return new RegistroFaltaJpaController(SManager.inicializar()).findRegistroFalta(k);
//    }
    @Override
    public List<RegistroFalta> buscarPorDni(int dni){
        return new RegistroFaltaJpaController(SManager.inicializar()).findRegistroFalta(dni);
    }
    @Override
    public List<RegistroFalta> buscarPorDia(int dia, int mes, int anio){
        return new RegistroFaltaJpaController(SManager.inicializar()).findRegistroFalta(dia);
    }
    @Override
    public List<RegistroFalta> buscarPorMes(int mes, int anio){
        return new RegistroFaltaJpaController(SManager.inicializar()).findRegistroFalta(mes);
    }
    @Override
    public List<RegistroFalta> buscarPorAnio(int anio){
        return new RegistroFaltaJpaController(SManager.inicializar()).findRegistroFalta(anio);
    }
    @Override
    public List<RegistroFalta> listaFaltas(){
        return new RegistroFaltaJpaController(SManager.inicializar()).findRegistroFaltaEntities();

    }
}
