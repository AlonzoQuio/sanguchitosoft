/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.modelo.cierrecajamodelo.implementacion;

import com.epissoft.sanguchito.modelo.cierrecajamodelo.GestionarCierreCaja;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.CierreCaja;
import com.epissoft.sanguchito.persistencia.jpa.CierreCajaJpaController;


public class GestionarCierreCajaImplementacion implements GestionarCierreCaja{
    @Override
    public boolean agregarCierreCaja(CierreCaja cr){
        CierreCajaJpaController cc=new CierreCajaJpaController(SManager.inicializar());
        try{
            cc.create(cr);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public CierreCaja buscarCierreCaja(int cod){
        return new CierreCajaJpaController(SManager.inicializar()).findCierreCaja(cod);
    }
}
