/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.tipofaltamodelo;

import java.util.List;
import com.epissoft.sanguchito.persistencia.TipoFalta;

/**
 *
 * @author HP
 */
public interface GestionarTipoFalta {
    public boolean insertarTipoFalta(TipoFalta tipoF);
    public boolean actualizarTipoFalta(TipoFalta tipoF);
    public boolean habilitarTipoFalta(int codigo);
    public boolean inhabilitarTipoFalta(int codigo);
    public TipoFalta buscarTipoFalta(int codigo);
    public TipoFalta buscarTipoFalta(String descripcion);
    public List<TipoFalta> listaTipoFaltas();
}
