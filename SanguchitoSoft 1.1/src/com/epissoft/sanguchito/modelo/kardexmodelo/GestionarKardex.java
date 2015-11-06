
package com.epissoft.sanguchito.modelo.kardexmodelo;

import com.epissoft.sanguchito.persistencia.Kardex;
import java.util.List;

public interface GestionarKardex {
    public boolean nuevoKardex(Kardex k);
    public List<Kardex> kardexProducto(int codigoProducto);
    public List<Kardex> listaKardex();
}
