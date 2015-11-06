
package com.epissoft.sanguchito.modelo.ingresokardexmodelo.implementacion;

import com.epissoft.sanguchito.modelo.ingresokardexmodelo.GestionarIngresoKardex;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.IngresoKardex;
import com.epissoft.sanguchito.persistencia.Kardex;
import com.epissoft.sanguchito.persistencia.jpa.IngresoKardexJpaController;

public class GestionarIngresoKardexImplementacion implements GestionarIngresoKardex{

    @Override
    public boolean nuevoIngresoKardex(IngresoKardex i, Kardex k) {
        IngresoKardexJpaController iC=new IngresoKardexJpaController(SManager.inicializar());
        i.setKardex(k);
        try{
            iC.create(i);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
