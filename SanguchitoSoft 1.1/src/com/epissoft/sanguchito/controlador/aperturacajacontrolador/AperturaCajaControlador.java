package com.epissoft.sanguchito.controlador.aperturacajacontrolador;

import com.epissoft.sanguchito.controlador.usuariocontrolador.UsuarioControlador;
import com.epissoft.sanguchito.modelo.aperturacajamodelo.implementacion.GestionarAperturaCajaImplementacion;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.AperturaCaja;
import com.epissoft.sanguchito.persistencia.jpa.FacturacionJpaController;
import com.epissoft.sanguchito.vista.ManejoSesion;
import java.sql.Time;
import java.util.Calendar;

public class AperturaCajaControlador {
    public static String agregarAperturaCaja(String mont,String obs){
        float monto;
        try{
            monto=Float.parseFloat(mont);
            
        }catch(Exception e){
            return "Error: Ingrese un monto correcto";
        }
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        Time hora=new Time(fecha.getTime().getHours(),fecha.getTime().getMinutes(),fecha.getTime().getSeconds());
        int numDocumento=new FacturacionJpaController(SManager.inicializar()).getFacturacionCount();
        AperturaCaja ap=new AperturaCaja(0, dia,mes,anio,hora,numDocumento,monto);
        ap.setAperCajObs(obs);
        ap.setUsuCod(UsuarioControlador.buscarUsuarioNom(ManejoSesion.getUser()));
        if(new GestionarAperturaCajaImplementacion().agregarAperturaCaja(ap)){
            return "CORRECTO";
        }else{
            return "Error: No se pudo realizar la apertura de caja";
        }
    }
    public static boolean ultimoCierre(){
        return new GestionarAperturaCajaImplementacion().ultimoCierre();
    }
    public static int inicioDocumento(){
        return new GestionarAperturaCajaImplementacion().inicioDocumento();
    }
    public static float montoApertura(){
        return new GestionarAperturaCajaImplementacion().montoApertura();
    }
}
