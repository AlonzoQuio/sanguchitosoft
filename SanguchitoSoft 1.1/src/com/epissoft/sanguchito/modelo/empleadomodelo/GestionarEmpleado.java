/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.empleadomodelo;

import java.util.List;
import com.epissoft.sanguchito.persistencia.Empleado;

/**
 *
 * @author HP
 */
public interface GestionarEmpleado {
    public boolean insertarEmpleado(Empleado e);
    public boolean actualizarEmpleado(Empleado e);
    public boolean inhabilitarEmpleado(int dni);
    public boolean habilitarEmpleado(int dni);
    public Empleado buscarEmpleado(int dni);
    public List<Empleado> buscarPorApellido(String categoria);
    public List<Empleado> buscarPorCargo(int cargo);
    public List<Empleado> buscarPorTurno(int turno);
    public List<Empleado> listaEmpleados(); 
}
