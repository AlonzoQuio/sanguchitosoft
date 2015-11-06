
package com.epissoft.sanguchito.modelo.kardexmodelo.implementacion;

import com.epissoft.sanguchito.modelo.kardexmodelo.GestionarKardex;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Kardex;
import com.epissoft.sanguchito.persistencia.jpa.KardexJpaController;
import java.util.List;

public class GestionarKardexImplementacion implements GestionarKardex{

    @Override
    public boolean nuevoKardex(Kardex k) {
        KardexJpaController kC=new KardexJpaController(SManager.inicializar());
        try{
            kC.create(k);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<Kardex> kardexProducto(int codigoProducto) {
        return new KardexJpaController(SManager.inicializar()).findKardexByProdAlm(codigoProducto);
    }

    @Override
    public List<Kardex> listaKardex() {
        return new KardexJpaController(SManager.inicializar()).findKardexEntities();
    }
    
}
