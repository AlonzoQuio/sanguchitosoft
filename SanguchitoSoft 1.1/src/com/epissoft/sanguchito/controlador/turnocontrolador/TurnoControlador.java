
package com.epissoft.sanguchito.controlador.turnocontrolador;

import java.util.List;
import com.epissoft.sanguchito.modelo.turnomodelo.implementacion.GestionarTurnoImplementacion;
import com.epissoft.sanguchito.modelo.turnomodelo.validacion.TurnoValidacion;
import com.epissoft.sanguchito.persistencia.Turno;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TurnoControlador {
    public static GestionarTurnoImplementacion gti;
    public TurnoControlador(){
        gti = new GestionarTurnoImplementacion();
    }
    public String insertarTurno(String descripcion, String hora_ini, String hora_fin){
        Date inicio;
        Date fin;
        try {
            SimpleDateFormat formato= new SimpleDateFormat("HH:mm");
            inicio = formato.parse(hora_ini);
            fin=formato.parse(hora_fin);
        } catch (ParseException ex) {
            return "Ingrese una hora de inicio y fin para el turno";
        }
        Turno t = new Turno(0,descripcion,inicio,fin,true);
        if(gti.insertarTurno(t)){
            return "CORRECTO";
        }else{
            return "La descripcion que desea utilizar, no esta disponible";
        }
    }
    public String actualizarTurno(int cod, String descrip, String hora_ini, String hora_fin){
        Date inicio;
        Date fin;
        try {
            SimpleDateFormat formato= new SimpleDateFormat("HH:mm");
            inicio = formato.parse(hora_ini);
            fin=formato.parse(hora_fin);
        } catch (ParseException ex) {
            return "Ingrese una hora de inicio y fin para el turno";
        }
        Turno t = new Turno(cod,descrip,inicio,fin,true);
        if(gti.actualizarTurno(t)){
            return "CORRECTO";
        }else{
            return "La descripcion que desea utilizar, no esta disponible";
        }
    }
    public boolean habilitarTurno(String codigo){
        Integer codi = Integer.parseInt(codigo);
        gti.habilitarTurno(codi);
        return true;
    }
    public boolean inhabilitarTurno(String codigo){
        Integer codi = Integer.parseInt(codigo);
        gti.inhabilitarTurno(codi);
        return true;
    }
    
    public Turno BuscarTurno(int codigo){
        if(!TurnoValidacion.existeTurno(codigo)){
            System.out.println("No existe el turno con codigo: " + codigo);
            return null;
        }
        return gti.BuscarTurno(codigo);
    }
    public Turno BuscarTurnoDescripcion(String descripcion){
        if(!TurnoValidacion.existeDescripcion(descripcion)){
            System.out.println("No existe el turno con des: " + descripcion);
            return null;
        }
        return gti.BuscarTurno(descripcion);
    }
    public static List<Turno> ListaTurnos(){
        return new GestionarTurnoImplementacion().ListaTurnos();
    }
}
