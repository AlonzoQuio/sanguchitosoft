
package com.epissoft.sanguchito.controlador.cargocontrolador;

import com.epissoft.sanguchito.controlador.utilitarios.ValidacionGeneral;
import java.util.List;
import com.epissoft.sanguchito.modelo.cargomodelo.implementacion.GestionarCargoImplementacion;
import com.epissoft.sanguchito.modelo.cargomodelo.validacion.CargoValidacion;
import com.epissoft.sanguchito.persistencia.Cargo;


public class CargoControlador {
    
    public static String insertarCargo(String descripcion,String observacion){
        GestionarCargoImplementacion gci=new GestionarCargoImplementacion();
        if(!ValidacionGeneral.tamValido(descripcion)){
            return "El nombre para el cargo no puede estar vacio";
        }
        if(CargoValidacion.existeDescripcion(descripcion)){
            return "El nombre de cargo que desea utilizar, ya esta siendo utilizado";
        }
        else{
            Cargo cargo = new Cargo(0,descripcion,observacion,true);
            gci.insertarCargo(cargo);
            return "CORRECTO";
        }        
    }
    public static String actualizarCargo(String codigo, String descripcion,String observacion){
        GestionarCargoImplementacion gci=new GestionarCargoImplementacion();
        int cod = Integer.parseInt(codigo);
        if(!ValidacionGeneral.tamValido(descripcion)){
            return "El nombre para el cargo no puede estar vacio";
        }
        if(CargoValidacion.existeCargo(cod) || CargoValidacion.existeDescripcion(descripcion) ){
            return "El nombre de cargo que desea utilizar, ya esta siendo utilizado";
        }
        else{
            Cargo cargo = new Cargo(cod,descripcion,observacion,true);
            gci.actualizarCargo(cargo);
            return "CORRECTO";
        }
    }
    public boolean habilitarCargo(int codigo){
        GestionarCargoImplementacion gci=new GestionarCargoImplementacion();
        if(!CargoValidacion.existeCargo(codigo)){
            return false;
        }
        else{
            gci.habilitarCargo(codigo);
            return true;
        }
    }
    public boolean inhabilitarCargo(int codigo){
        GestionarCargoImplementacion gci=new GestionarCargoImplementacion();
        if(!CargoValidacion.existeCargo(codigo)){
            return false;
        }
        else{
            gci.inhabilitarCargo(codigo);
            return true;
        }
    }
    public Cargo buscarCargo(int codigo){
        GestionarCargoImplementacion gci=new GestionarCargoImplementacion();
        if(!CargoValidacion.existeCargo(codigo)){
            //System.out.println("No existe el cargo con codigo: " + codigo);
            return null;
        }
        else{
            return gci.buscarCargo(codigo);            
        }
    }
    public Cargo buscarCargo(String descripcion){
        GestionarCargoImplementacion gci=new GestionarCargoImplementacion();
        if(!CargoValidacion.existeDescripcion(descripcion)){
            return null;
        }
        else
            return gci.buscarCargo(descripcion);
    }
    
    public static List<Cargo> listaCargos(){
        return new GestionarCargoImplementacion().listaCargos();
    }
}
