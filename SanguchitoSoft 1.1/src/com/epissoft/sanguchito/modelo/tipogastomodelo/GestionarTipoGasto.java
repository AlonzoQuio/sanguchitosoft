package com.epissoft.sanguchito.modelo.tipogastomodelo;

import com.epissoft.sanguchito.persistencia.TipoGasto;
import java.util.List;

public interface GestionarTipoGasto {
    public boolean agregarTipoGasto(TipoGasto tp);
    public boolean actualizarTipoGasto(TipoGasto tp);
    public List<TipoGasto> listaTipoGasto();
}
