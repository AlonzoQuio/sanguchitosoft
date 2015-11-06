

package com.epissoft.sanguchito.modelo.facturacionmodelo;
import com.epissoft.sanguchito.persistencia.Facturacion;
import java.util.List;

public interface GestionarFacturacion {
    public boolean insertarFacturacion(Facturacion cargo);
    public List<Facturacion> facturasPeriodo(int inicio,int fin);
    public int facturaAcual();
}
