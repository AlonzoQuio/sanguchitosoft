
package com.epissoft.sanguchito.modelo.facturacionextramodelo.implementacion;

import com.epissoft.sanguchito.modelo.facturacionextramodelo.GestionarFacturacionExtra;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.FacturaciondExtra;
import com.epissoft.sanguchito.persistencia.jpa.FacturaciondExtraJpaController;

public class FacturacionExtraImplementacion implements GestionarFacturacionExtra{
    @Override
    public boolean insertarFacturacionExtraDetalle(FacturaciondExtra fe){
        FacturaciondExtraJpaController feController = new FacturaciondExtraJpaController(SManager.inicializar());
        try{
            feController.create(fe);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
