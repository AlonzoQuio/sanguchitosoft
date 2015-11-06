package com.epissoft.sanguchito.modelo.unidadmedidamodelo.implementacion;

import com.epissoft.sanguchito.modelo.unidadmedidamodelo.GestionarUnidadMedida;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.UnidadMedida;
import com.epissoft.sanguchito.persistencia.jpa.UnidadMedidaJpaController;
import java.util.List;

public class GestionarUnidadMedidaImplementacion implements GestionarUnidadMedida{

    @Override
    public boolean agregarUnidadMedida(UnidadMedida u) {
        UnidadMedidaJpaController uC= new UnidadMedidaJpaController(SManager.inicializar());
        try{
            uC.create(u);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean modificarUnidadMedida(UnidadMedida u) {
        UnidadMedidaJpaController uC= new UnidadMedidaJpaController(SManager.inicializar());
        UnidadMedida unidad=uC.findUnidadMedida(u.getUniMedCod());
        try{
            unidad.setUniMedDes(u.getUniMedDes());
            uC.edit(unidad);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<UnidadMedida> listaUnidadMedida() {
        return new UnidadMedidaJpaController(SManager.inicializar()).findUnidadMedidaEntities();
    }
    
}
