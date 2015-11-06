/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.registrofaltamodelo;

import java.util.List;
import com.epissoft.sanguchito.persistencia.RegistroFalta;
//import com.epissoft.sanguchito.persistencia.RegistroFaltaPK;

/**
 *
 * @author HP
 */
public interface GestionarRegistroFalta {
    public boolean insertarFalta(RegistroFalta e);
    //public RegistroFalta buscarFalta(RegistroFaltaPK k);
    public List<RegistroFalta> buscarPorDni(int dni);
    public List<RegistroFalta> buscarPorDia(int dia, int mes, int anio);
    public List<RegistroFalta> buscarPorMes(int mes, int anio);
    public List<RegistroFalta> buscarPorAnio(int anio);
    public List<RegistroFalta> listaFaltas(); 
}
