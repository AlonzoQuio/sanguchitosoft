package com.epissoft.sanguchito.modelo.egresokardexmodelo.implementacion;

import com.epissoft.sanguchito.modelo.egresokardexmodelo.GestionarEgresoKardex;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.EgresoKardex;
import com.epissoft.sanguchito.persistencia.Kardex;
import com.epissoft.sanguchito.persistencia.jpa.EgresoKardexJpaController;

public class GestionarEgresoKardexImplementacion implements GestionarEgresoKardex{

    @Override
    public boolean nuevoEgresoKardex(EgresoKardex i, Kardex k) {
        EgresoKardexJpaController eC=new EgresoKardexJpaController(SManager.inicializar());
        i.setKardex(k);
        try{
            eC.create(i);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
