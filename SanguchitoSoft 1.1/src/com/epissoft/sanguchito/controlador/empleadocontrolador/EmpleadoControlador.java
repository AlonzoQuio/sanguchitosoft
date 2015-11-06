/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.controlador.empleadocontrolador;

import com.epissoft.sanguchito.controlador.cargocontrolador.CargoControlador;
import com.epissoft.sanguchito.controlador.turnocontrolador.TurnoControlador;
import com.epissoft.sanguchito.controlador.utilitarios.ImageResizer;
import java.util.List;
import com.epissoft.sanguchito.modelo.empleadomodelo.implementacion.GestionarEmpleadoImplementacion;
import com.epissoft.sanguchito.modelo.empleadomodelo.validacion.EmpleadoValidacion;
import com.epissoft.sanguchito.persistencia.Cargo;
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.Turno;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author HP
 */
public class EmpleadoControlador {
    public static GestionarEmpleadoImplementacion gei;
    public static EmpleadoValidacion ev;
    public EmpleadoControlador(){
        gei = new GestionarEmpleadoImplementacion();
        ev = new EmpleadoValidacion();
    }
    
    public ArrayList<ArrayList<String>> getEmpleados(String dni, String nombre){
        ArrayList<ArrayList<String>> empleados = new ArrayList<ArrayList<String>>();
        if(dni.equals("")){
            List<Empleado> emps = buscarPorApellido(nombre);
            int lon =  emps.size();
            if(lon == 0){
                return empleados;
            }
            Iterator<Empleado> iter = emps.iterator();
            while(iter.hasNext()){
                Empleado e = iter.next();
                if(e != null){
                    TurnoControlador tc = new TurnoControlador();
                    CargoControlador cc = new CargoControlador();
                    ArrayList<String> emp = new ArrayList<String>();
                    dni = "" +  e.getEmpDni();
                    String name = e.getEmpNom() + " " +e.getEmpApePat() + " " + e.getEmpApeMat();
                    String dir = e.getEmpDir() + " - " + e.getEmpDirDis();
                    String tlfn = "" + e.getEmpTel1();
                    String estado = e.getEmpEst()?"Habilitado":"Inhabilitado";
                    String turno = tc.BuscarTurno(e.getTurCod().getTurCod()).getTurDes();
                    System.out.println(turno + "  Cargo: " + e.getCarCod().getCarCod());
                    String cargo = cc.buscarCargo(e.getCarCod().getCarCod()).getCarDes();
                    emp.add(dni);
                    emp.add(name);
                    emp.add(dir);
                    emp.add(turno);
                    emp.add(cargo);
                    emp.add(tlfn);
                    emp.add(estado);
                    empleados.add(emp);                
                }
                else{
                    return empleados;
                }
            }
        }
        else{
            System.out.println("Buscando Empleado por DNI");
            Empleado e = buscarEmpleado(dni);
            if(e != null){
                TurnoControlador tc = new TurnoControlador();
                CargoControlador cc = new CargoControlador();
                ArrayList<String> emp = new ArrayList<String>();
                String name = e.getEmpNom() + " " +e.getEmpApePat() + " " + e.getEmpApeMat();
                String dir = e.getEmpDir() + " - " + e.getEmpDirDis();
                String tlfn = "" + e.getEmpTel1();
                String estado = e.getEmpEst()?"Habilitado":"Inhabilitado";
                String turno = tc.BuscarTurno(e.getTurCod().getTurCod()).getTurDes();
                System.out.println(turno + "  Cargo: " + e.getCarCod().getCarCod());
                String cargo = cc.buscarCargo(e.getCarCod().getCarCod()).getCarDes();
                emp.add(dni);
                emp.add(name);
                emp.add(dir);
                emp.add(turno);
                emp.add(cargo);
                emp.add(tlfn);
                emp.add(estado);
                empleados.add(emp);                
            }
            else{
                return empleados;
            }
        }
        return empleados;
    }
    
    public String insertarEmpleado(String dni, String nombre, String ape_pat, String ape_mat, String dir, String distrito, String tel1, String tel2, String sueldo, String turno, String cargo, String url ){
        
        if(EmpleadoValidacion.existeDNI(dni)){
            return "El DNI no es valido o ya esta registrado.";
        }
        else if(!(ev.correctosNombre(nombre, ape_pat, ape_mat) && ev.correctoSueldo(sueldo) && ev.correctosTelefonos(tel1, tel2))){
            
            return "Datos incorrectos, revise por favor";
        }
        else{
            CargoControlador cc = new CargoControlador();
            TurnoControlador tc = new TurnoControlador();            
            if(cc.buscarCargo(cargo) == null){
                return "El Cargo no es valido";
            }
            if(tc.BuscarTurnoDescripcion(turno) == null){
                return "El turno no es valido";
            }
            Integer dni_c = Integer.parseInt(dni);
            float sueldo_c = Float.parseFloat(sueldo);
            Empleado emp = new Empleado(dni_c,nombre, ape_pat,ape_mat, dir, distrito,sueldo_c,true);
            try{
                emp.setEmpTel1(Integer.parseInt(tel1));
            }catch(NumberFormatException e){
                
            }
            try{
                emp.setEmpTel2(Integer.parseInt(tel2));
            }
            catch(NumberFormatException e){
                
            }
            emp.setTurCod(tc.BuscarTurnoDescripcion(turno));
            emp.setCarCod(cc.buscarCargo(cargo));
            if(gei.insertarEmpleado(emp)){
                try{
                    ImageResizer ir = new ImageResizer();
                    ir.copyImage(url, "Empleado/emp" + dni, 300, 300);
                }catch(Exception e){
                    return "Seleccione una imagen para el empleado";
                }
                return "CORRECTO";
            }
            else{
                return "Hubo un problema en el registro, revise los datos.";
            }
        }
    }
    public String actualizarEmpleado(String dni,String tel1, String tel2, String dir, String dis, String suel, String cargo, String turno){
        
        if(!(ev.correctoSueldo(suel) && ev.correctosTelefonos(tel1, tel2))){            
            return "Datos incorrectos, revise por favor";
        }
        else{
            Integer dni_c = Integer.parseInt(dni);
            CargoControlador cc = new CargoControlador();
            TurnoControlador tc = new TurnoControlador();            
            if(cc.buscarCargo(cargo) == null){
                return "El Cargo no es valido";
            }
            if(tc.BuscarTurnoDescripcion(turno) == null){
                return "El turno no es valido";
            }
            Empleado emp = gei.buscarEmpleado(dni_c);
            emp.setEmpDir(dir);
            emp.setEmpDirDis(dis);
            try{
                emp.setEmpTel1(Integer.parseInt(tel1));
            }catch(NumberFormatException e){
                
            }
            try{
                emp.setEmpTel2(Integer.parseInt(tel2));
            }
            catch(NumberFormatException e){
                
            }
            emp.setEmpSuel(Float.parseFloat(suel));
            emp.setCarCod(cc.buscarCargo(cargo));
            emp.setTurCod(tc.BuscarTurnoDescripcion(turno) );
            gei.actualizarEmpleado(emp);
            return "Se guardaron los datos satisfactoriamente";
        }
    }
    public String inhabilitarEmpleado(String dni){
        Integer dni_c = Integer.parseInt(dni);
        gei.inhabilitarEmpleado(dni_c);
        return gei.inhabilitarEmpleado(dni_c)? "Se inhabilito al empleado":"Ocurrio un error";
    }
    public String habilitarEmpleado(String dni){
        Integer dni_c = Integer.parseInt(dni);
        return gei.habilitarEmpleado(dni_c)? "Se habilito al empleado":"Ocurrio un error";

    }   
    public static Empleado buscarEmpleado(String dni){
        Integer dni_c = 0;
        try{
        dni_c = Integer.parseInt(dni);
        }catch(Exception e){
            return null;
        }
        return new GestionarEmpleadoImplementacion().buscarEmpleado(dni_c);
    }
    public List<Empleado> buscarPorApellido(String apellido){
        return gei.buscarPorApellido(apellido);
    }
    public List<Empleado> buscarPorCargo(int cargo){
        return gei.buscarPorCargo(cargo);
    }
    public List<Empleado> buscarPorTurno(int turno){
        return gei.buscarPorTurno(turno);
    }
    public List<Empleado> listaEmpleados(){
        return gei.listaEmpleados();
    }    
}
