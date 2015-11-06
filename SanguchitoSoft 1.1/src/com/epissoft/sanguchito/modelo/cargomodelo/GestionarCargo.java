/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.cargomodelo;

import java.util.List;
import com.epissoft.sanguchito.persistencia.Cargo;

/**
 *
 * @author HP
 */
public interface GestionarCargo {
    public boolean insertarCargo(Cargo cargo);
    public boolean actualizarCargo(Cargo cargo);
    public boolean habilitarCargo(int codigo);
    public boolean inhabilitarCargo(int codigo);
    public Cargo buscarCargo(int codigo);
    public Cargo buscarCargo(String descripcion);
    public List<Cargo> listaCargos();
}
