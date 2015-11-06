/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.modelo.unidadmedidamodelo;

import com.epissoft.sanguchito.persistencia.UnidadMedida;
import java.util.List;

/**
 *
 * @author Hisae Shizumaru
 */
public interface GestionarUnidadMedida {
    public boolean agregarUnidadMedida(UnidadMedida u);
    public boolean modificarUnidadMedida(UnidadMedida u);
    public List<UnidadMedida> listaUnidadMedida();
}
