package com.epissoft.sanguchito.modelo.tipogastomodelo.implementacion;

import com.epissoft.sanguchito.modelo.tipogastomodelo.GestionarTipoGasto;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.TipoGasto;
import com.epissoft.sanguchito.persistencia.jpa.TipoGastoJpaController;
import java.util.List;

public class GestionarTipoGastoImplementacion implements GestionarTipoGasto{

    @Override
    public boolean agregarTipoGasto(TipoGasto tp) {
        TipoGastoJpaController tC=new TipoGastoJpaController(SManager.inicializar());
        try{
            tC.create(tp);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean actualizarTipoGasto(TipoGasto tp) {
        TipoGastoJpaController tC=new TipoGastoJpaController(SManager.inicializar());
        TipoGasto t=tC.findTipoGasto(tp.getTipGasCod());
        try{
            t.setTipGasDes(tp.getTipGasDes());
            tC.edit(t);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<TipoGasto> listaTipoGasto() {
        return new TipoGastoJpaController(SManager.inicializar()).findTipoGastoEntities();
    }
    
}
