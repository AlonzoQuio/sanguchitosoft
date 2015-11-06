
package com.epissoft.sanguchito.modelo.ingresokardexmodelo;

import com.epissoft.sanguchito.persistencia.IngresoKardex;
import com.epissoft.sanguchito.persistencia.Kardex;

public interface GestionarIngresoKardex {
    public boolean nuevoIngresoKardex(IngresoKardex i,Kardex k);
}
