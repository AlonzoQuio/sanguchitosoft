
package com.epissoft.sanguchito.modelo.aperturacajamodelo;

import com.epissoft.sanguchito.persistencia.AperturaCaja;
import java.util.List;

public interface GestionarAperturaCaja {
    public boolean agregarAperturaCaja(AperturaCaja ap);
    public List<AperturaCaja> listaAperturaCaja(int dia,int mes,int anio);
    public boolean ultimoCierre();
    public int inicioDocumento();
    public float montoApertura();
}
