
package com.epissoft.sanguchito.modelo.facturacionmodelo.implementacion;

import com.epissoft.sanguchito.modelo.facturacionmodelo.GestionarFacturacion;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Facturacion;
import com.epissoft.sanguchito.persistencia.jpa.FacturacionJpaController;
import java.util.List;

public class GestionarFacturacionImplentacion implements GestionarFacturacion {
    @Override
    public boolean insertarFacturacion(Facturacion factura){
        FacturacionJpaController facturaController = new FacturacionJpaController(SManager.inicializar());
        try{
            facturaController.create(factura);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public List<Facturacion> facturasPeriodo(int inicio,int fin){
        FacturacionJpaController facturaController = new FacturacionJpaController(SManager.inicializar());
        return facturaController.findFacturacionEntities(fin-inicio,inicio);
    }
    @Override
    public int facturaAcual(){
        FacturacionJpaController facturaController = new FacturacionJpaController(SManager.inicializar());
        return facturaController.getFacturacionCount();
    }
}
