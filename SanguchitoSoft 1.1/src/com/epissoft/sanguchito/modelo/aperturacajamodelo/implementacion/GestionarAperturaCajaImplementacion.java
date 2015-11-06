
package com.epissoft.sanguchito.modelo.aperturacajamodelo.implementacion;

import com.epissoft.sanguchito.modelo.aperturacajamodelo.GestionarAperturaCaja;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.AperturaCaja;
import com.epissoft.sanguchito.persistencia.jpa.AperturaCajaJpaController;
import com.epissoft.sanguchito.persistencia.jpa.CierreCajaJpaController;
import java.util.List;


public class GestionarAperturaCajaImplementacion implements GestionarAperturaCaja{
    @Override
    public boolean agregarAperturaCaja(AperturaCaja ap){
        AperturaCajaJpaController aC=new AperturaCajaJpaController(SManager.inicializar());
        try{
            aC.create(ap);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public List<AperturaCaja> listaAperturaCaja(int dia,int mes,int anio){
        return new AperturaCajaJpaController(SManager.inicializar()).findAperturaCajaEntities();
    }
    @Override
    public boolean ultimoCierre(){
        AperturaCajaJpaController aC=new AperturaCajaJpaController(SManager.inicializar());
        CierreCajaJpaController cC=new CierreCajaJpaController(SManager.inicializar());
        if(aC.getAperturaCajaCount()==cC.getCierreCajaCount()){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public int inicioDocumento(){
        AperturaCajaJpaController aC=new AperturaCajaJpaController(SManager.inicializar());
        AperturaCaja ap=aC.findAperturaCaja(aC.getAperturaCajaCount());
        return ap.getAperFacIni();
    }
    @Override
    public float montoApertura(){
        AperturaCajaJpaController aC=new AperturaCajaJpaController(SManager.inicializar());
        AperturaCaja ap=aC.findAperturaCaja(aC.getAperturaCajaCount());
        return ap.getAperMontIni();
    }
}
