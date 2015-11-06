/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.turnomodelo;

import java.util.List;
import com.epissoft.sanguchito.persistencia.Turno;

/**
 *
 * @author HP
 */
public interface GestionarTurno {
    public boolean insertarTurno(Turno turno);
    public boolean actualizarTurno(Turno turno);
    public boolean habilitarTurno(int codigo);
    public boolean inhabilitarTurno(int codigo);
    public Turno BuscarTurno(int codigo);
    public Turno BuscarTurno(String descripcion);
    public List<Turno> ListaTurnos();
}
