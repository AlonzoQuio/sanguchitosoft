/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.controlador.registrofaltacontrolador;

import com.epissoft.sanguchito.controlador.empleadocontrolador.EmpleadoControlador;
import com.epissoft.sanguchito.controlador.turnocontrolador.TurnoControlador;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.epissoft.sanguchito.modelo.registrofaltamodelo.implementacion.GestionarRegistroFaltaImplementacion;
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.RegistroFalta;
//import com.epissoft.sanguchito.persistencia.RegistroFaltaPK;
import com.epissoft.sanguchito.persistencia.TipoFalta;
import com.epissoft.sanguchito.persistencia.Turno;
import java.util.Calendar;

/**
 *
 * @author HP
 */
public class RegistroFaltaControlador {
    public static GestionarRegistroFaltaImplementacion grf;
    public RegistroFaltaControlador(){
        grf = new GestionarRegistroFaltaImplementacion();        
    }
    public int insertarFalta(String cod ,boolean hora){
        /*Calculo fecha*/
        EmpleadoControlador ec = new EmpleadoControlador();
        TurnoControlador tc = new TurnoControlador();
        int tipo;
        String dia,mes,anio;
        Calendar fecha = Calendar.getInstance();
        anio = "" + fecha.get(Calendar.YEAR);
        mes = "" + fecha.get(Calendar.MONTH);
        dia = "" + fecha.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date ahora = null;
        String time = sdf.format(new Date());
        if(!hora) 
            time = "00:00:00"; 
        try{
            ahora = sdf.parse(time);
        }catch(Exception e){
            System.err.println("error de hora");
        }
        
        RegistroFalta rf = new RegistroFalta(Integer.parseInt(cod));
        rf.setRegFalFecDia(Integer.parseInt(dia));
        rf.setRegFalFecMes(Integer.parseInt(mes));
        rf.setRegFalFecAnio(Integer.parseInt(anio));
        Empleado emp = ec.buscarEmpleado(cod);
        Turno t = tc.BuscarTurno(emp.getTurCod().getTurCod());
        //tarde
        if(t.getTurHorIni().before(ahora)){
            tipo = 2;
        }
        //puntual
        else{
            tipo = 1;
        }
        rf.setRegFaltHor(ahora);
        rf.setTipFalCod(new TipoFalta(tipo));
        rf.setEmpDni(new Empleado(47630984));
        grf.insertarFalta(rf);        
        return tipo;
    }
//    public RegistroFalta buscarFalta(String cod, String dia, String mes, String anio){
//        Integer cod_c = Integer.parseInt(cod);
//        RegistroFaltaPK rfpk = new RegistroFaltaPK(cod_c,Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(anio));
//        return grf.buscarFalta(rfpk);
//    }
    public List<RegistroFalta> buscarPorDni(String dni){
        Integer cod_c = Integer.parseInt(dni);
        return grf.buscarPorDni(cod_c);
    }
    public List<RegistroFalta> buscarPorDia(int dia, int mes, int anio){
        return null;
    }
    public List<RegistroFalta> buscarPorMes(int mes, int anio){
        return null;
    }
    public List<RegistroFalta> buscarPorAnio(String anio){
        Integer cod_c = Integer.parseInt(anio);
        return grf.buscarPorAnio(cod_c);
    }
    public List<RegistroFalta> listaFaltas(){
        return grf.listaFaltas();
    }

}
