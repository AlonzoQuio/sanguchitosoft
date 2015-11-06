
package com.epissoft.sanguchito.controlador.cierrecajacontrolador;

import com.epissoft.sanguchito.modelo.cierrecajamodelo.implementacion.GestionarCierreCajaImplementacion;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.CierreCaja;
import com.epissoft.sanguchito.persistencia.jpa.AperturaCajaJpaController;
import com.epissoft.sanguchito.persistencia.jpa.FacturacionJpaController;
import java.sql.Time;
import java.util.Calendar;


public class CierreCajaControlador {
    public static String agregarCierreCaja(String obs){
        
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        Time hora=new Time(fecha.getTime().getHours(),fecha.getTime().getMinutes(),fecha.getTime().getSeconds());
        FacturacionJpaController fac=new FacturacionJpaController(SManager.inicializar());
        int numDocumento=fac.getFacturacionCount();
        AperturaCajaJpaController ap=new AperturaCajaJpaController(SManager.inicializar());
        int aperturaCaja=ap.getAperturaCajaCount();
        float monto=0;
        for(int i=aperturaCaja;i<numDocumento;i++){
            monto+=fac.findFacturacion(i).getFacTot();
        }
        CierreCaja c=new CierreCaja(aperturaCaja,dia,mes,anio,hora,numDocumento,monto);
        c.setCierCajObs(obs);
        if(new GestionarCierreCajaImplementacion().agregarCierreCaja(c)){
            return "CORRECTO";
        }else{
            return "Error: No se pudo realizar el cierre de caja";
        }
    }
}
