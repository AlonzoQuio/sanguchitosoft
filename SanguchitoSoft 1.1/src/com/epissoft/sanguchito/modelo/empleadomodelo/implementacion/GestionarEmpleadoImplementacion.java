/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.modelo.empleadomodelo.implementacion;

import java.util.List;
import com.epissoft.sanguchito.modelo.empleadomodelo.GestionarEmpleado;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.jpa.EmpleadoJpaController;

/**
 *
 * @author HP
 */
public class GestionarEmpleadoImplementacion implements GestionarEmpleado{
    @Override
    public boolean insertarEmpleado(Empleado emp){
        EmpleadoJpaController eC = new EmpleadoJpaController(SManager.inicializar());
        try{
            eC.create(emp);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean actualizarEmpleado(Empleado emp){
        EmpleadoJpaController eC = new EmpleadoJpaController(SManager.inicializar());
        Empleado empleado = eC.findEmpleado(emp.getEmpDni());
        try{
            empleado.setEmpTel1(emp.getEmpTel1());
            empleado.setEmpTel2(emp.getEmpTel2());
            empleado.setEmpDir(emp.getEmpDir());
            empleado.setEmpDirDis(emp.getEmpDirDis());  
            empleado.setEmpSuel(emp.getEmpSuel());
            empleado.setCarCod(emp.getCarCod());
            empleado.setTurCod(emp.getTurCod());
            eC.edit(empleado);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarEmpleado(int dni){
        EmpleadoJpaController eC = new EmpleadoJpaController(SManager.inicializar());
        Empleado emp = eC.findEmpleado(dni);
        try{
            emp.setEmpEst(false);
            eC.edit(emp);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarEmpleado(int dni){
        EmpleadoJpaController eC = new EmpleadoJpaController(SManager.inicializar());
        Empleado producto = eC.findEmpleado(dni);
        try{
            producto.setEmpEst(true);
            eC.edit(producto);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public Empleado buscarEmpleado(int dni){
        return new EmpleadoJpaController(SManager.inicializar()).findEmpleado(dni);
    }
    @Override
    public List<Empleado> buscarPorApellido(String apellido){
        return new EmpleadoJpaController(SManager.inicializar()).findEmpleado(apellido);
    }
    @Override
    public List<Empleado> buscarPorCargo(int cargo){
        return new EmpleadoJpaController(SManager.inicializar()).findEmpleadoCargo(cargo);
    }
    @Override
    public List<Empleado> buscarPorTurno(int turno){
        return new EmpleadoJpaController(SManager.inicializar()).findEmpleadoTurno(turno);
    }
    @Override
    public List<Empleado> listaEmpleados(){
        return new EmpleadoJpaController(SManager.inicializar()).findEmpleadoEntities();
    }
}
