
package com.epissoft.sanguchito.modelo.cierrecajamodelo;

import com.epissoft.sanguchito.persistencia.CierreCaja;

public interface GestionarCierreCaja {
    public boolean agregarCierreCaja(CierreCaja cr);
    public CierreCaja buscarCierreCaja(int cod);
}
