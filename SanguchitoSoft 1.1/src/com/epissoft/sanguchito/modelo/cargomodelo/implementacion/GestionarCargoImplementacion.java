
package com.epissoft.sanguchito.modelo.cargomodelo.implementacion;

import java.util.List;
import com.epissoft.sanguchito.modelo.cargomodelo.GestionarCargo;
import com.epissoft.sanguchito.modelo.utilitarios.SManager;
import com.epissoft.sanguchito.persistencia.Cargo;
import com.epissoft.sanguchito.persistencia.jpa.CargoJpaController;


public class GestionarCargoImplementacion implements GestionarCargo{
    @Override
    public boolean insertarCargo(Cargo cargo){
        CargoJpaController cargoController = new CargoJpaController(SManager.inicializar());
        try{
            cargoController.create(cargo);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean actualizarCargo(Cargo cargo){
        CargoJpaController cargoController = new CargoJpaController(SManager.inicializar());
        Cargo t = cargoController.findCargo(cargo.getCarCod());
        try{
            t.setCarDes(cargo.getCarDes());            
            cargoController.edit(t);
            return true;                    
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean habilitarCargo(int codigo){
        CargoJpaController cargoController = new CargoJpaController(SManager.inicializar());
        Cargo t = cargoController.findCargo(codigo);
        try{
            t.setCarEst(true);
            cargoController.edit(t);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean inhabilitarCargo(int codigo){
        CargoJpaController cargoController = new CargoJpaController(SManager.inicializar());
        Cargo t = cargoController.findCargo(codigo);
        try{
            t.setCarEst(false);
            cargoController.edit(t);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public Cargo buscarCargo(int codigo){
        return new CargoJpaController(SManager.inicializar()).findCargo(codigo);
    }
    @Override
    public Cargo buscarCargo(String descripcion){
        return new CargoJpaController(SManager.inicializar()).findCargo(descripcion);
    }
    @Override
    public List<Cargo> listaCargos(){
        return new CargoJpaController(SManager.inicializar()).findCargoEntities();        
    }
}
