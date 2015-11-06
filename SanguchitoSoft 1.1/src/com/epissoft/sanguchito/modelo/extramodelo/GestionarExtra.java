/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.extramodelo;

import com.epissoft.sanguchito.persistencia.Extras;
import java.util.List;

/**
 *
 * @author HP
 */
public interface GestionarExtra {
    public boolean insertarExtra(Extras cargo);
    public boolean actualizarExtra(Extras cargo);
    public boolean habilitarExtra(int codigo);
    public boolean inhabilitarExtra(int codigo);
    public Extras buscarExtras(int codigo);
    public List<Extras> buscarExtrasPorCategoria(int codCat);
    public List<Extras> buscarExtrasInhabilitadosPorCategoria(int codCat);
    public List<Extras> listaExtras();
    public List<Extras> listaExtrasInhabilitados();
    
}
