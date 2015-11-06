package com.epissoft.sanguchito.modelo.gastosmodelo.implementacion;

import com.epissoft.sanguchito.modelo.gastosmodelo.GestionarGastos;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Gastos;
import com.epissoft.sanguchito.persistencia.jpa.GastosJpaController;

public class GestionarGastosImplementacion implements GestionarGastos{

    @Override
    public boolean agregarGasto(Gastos g) {
        GastosJpaController gC=new GastosJpaController(SManager.inicializar());
        try{
            gC.create(g);
            return true;
        }catch(Exception e){
            return false;
        }
    }    
}
